import scrapy
import re
import json
import csv
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
from dining_code.items import DiningCodeItem, DingingUrlItem
from scrapy.utils.markup import remove_tags


class testSpider(scrapy.Spider):
    name = "test"

    def __init__(self):
        self.idx = 1
        self.category = 0
        self.sub_location = 0

    def timeparser(self, tag):
        1

    def start_requests(self):
        yield scrapy.Request("https://www.diningcode.com/profile.php?rid=e46nJyfLKSkS", self.parse_rest)


    def parse_rest(self, response):
        item = DiningCodeItem()

        # item['idx'] = self.idx
        # item['name'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[1]/div[2]/p').extract()[0])
        # print(response.xpath('//*[@id="div_profile"]/div[1]/div[3]'))
        # item['food'] = self.htmlparser_01(response.xpath('//*[@id="div_profile"]/div[1]/div[3]'))


        path = response.xpath('//*[@class="busi-hours short"]/ul/li').extract()
        schedule = {"월": "휴무일", "화": "휴무일", "수": "휴무일", "목": "휴무일", "금": "휴무일", "토": "휴무일", "일": "휴무일"}
        a = []

        if path:
            for row in path:
                a.append(remove_tags(row).split()) # 월~ 화 까지 dictionary로 구분해서 지정(휴무포함)
            for sale in a:
                if sale[0] == "휴무일":
                    continue
                open_date = sale[0]  # 매일, 월-토,일, 월-금,토, 휴무일

                noon = 0
                clock = 0
                cnt = 0
                start_end = ''
                for ele in sale:
                    if len(start_end) >= 8:
                        break
                    if ele == "오후":
                        noon = 1
                        clock += 1
                    else:
                        if ele.find("시"):
                            if noon == 1:
                                start_end += str(int(''.join(re.findall("\d+", ele))) + 12)
                                noon = 0
                            else:
                                start_end += ''.join(re.findall("\d+", ele))

                            if cnt == 0 and len(start_end) == 2 and clock == 1:
                                start_end += "00"
                                cnt += 1
                        elif ele.find("분"):
                            cnt += 1
                            start_end += ''.join(re.findall("\d+", ele))
                if cnt == 1:
                    start_end += "00"





                print(start_end)





        print(a)
        for i, val in schedule.items():
            print(i, val)

        # item['score'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[1]/div[4]/p/strong').extract()[0])
        # item['like'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[1]/div[5]/a[1]/span/i').extract()[0])
        # item['location'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[2]/ul/li[2]').extract()[0])
        # item['tel'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[2]/ul/li[3]').extract()[0])
        # item['tag'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[2]/ul/li[4]').extract()[0])
        # item['type'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[2]/ul/li[5]').extract()[0])
        #
        # item['time'] = response.xpath('').extract()[0]
        # item['price'] = remove_tags(response.xpath('//*[@id="div_detail"]/div[2]/ul/li[1]/p[2]').extract()[0])
        # item['star'] = remove_tags(response.xpath('//*[@id="lbl_review_point"]').extract()[0])
        # item['good_percent'] = response.xpath('').extract()[0]
        # item['bad_percent'] = response.xpath('').extract()[0]
        self.idx += 1


        time.sleep(3)

        yield item
