import scrapy
import re
import json
import csv
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
from dining_code.items import DiningCodeItem, DingingUrlItem
from scrapy.utils.markup import remove_tags
from scrapy.selector import Selector


class DiningCodURLSpider(scrapy.Spider):  # jMap[0]['lat']  jMap[0]['lng']
    name = "diningurl"
    allowed_domains = ['diningcode.com']
    start_urls = ["https://www.diningcode.com/list.php?query="]

    def __init__(self):
        scrapy.Spider.__init__(self)
        self.browser = webdriver.Chrome("C:/Users/an004/chromedriver.exe")
        time.sleep(1)

    def parse(self, response):
        Dong = ["서울중구", "명동", "소공동", "회현동", "필동", "장충동", "광희동", "을지로동", "신당동", "다산동", "약수동", "청구동", "동화동", "황학동", "중림동"]
        Category = ["배달", "백년가게", "수요미식회", "생활의달인", "TV맛집", "한식", "중식", "일식", "카페", "술집", "고기집", "횟집", "해산물", "밥집",
                    "분식", "패스트푸드", "파스타", "뷔페", "국물요리", "면요리", "이탈리안", "멕시칸", "프렌치", "아시안"]
        item = DingingUrlItem()

        for dong in Dong:
            for cate in Category:
                self.browser.get(response.url + dong + "%20" + cate)
                print(response.url + dong + "%20" + cate)
                time.sleep(2)
                while True:
                    try:
                        self.browser.find_element_by_xpath('//*[@id="div_list_more"]/a').send_keys(Keys.ENTER)
                    except:
                        break
                    time.sleep(3)

                response_selenium = Selector(text=self.browser.page_source)

                rows = response_selenium.xpath('//*[@id="div_list"]/li[not(@id)]')
                i = -1
                for idx, row in enumerate(rows):
                    if i < 2:
                        i += 1
                        continue
                    item['url'] = row.xpath('//*[@id="div_list"]/li[{}]/a/@href'.format(idx)).extract()[0]
                    item['url'] = "https://www.diningcode.com" + item['url']
                    item['sub_location'] = dong
                    item['category'] = cate

                    i += 1
                    if i == 12:
                        i = 0

                    yield item


class DiningCodSpider(scrapy.Spider):  # jMap[0]['lat']  jMap[0]['lng']
    name = "dining"

    def __init__(self):
        self.idx = 1
        self.category = 0
        self.sub_location = 0

    def htmlparser(self, tag):
        html_list = []
        for row in tag:
            html_list.append(row.xpath('text()').extract()[0].strip())
        return html_list

    def timeparser(self, tag):
        schedule = {"mon": 1, "tue": 1, "wed": 1, "thu": 1, "fri": 1, "sta": 1, "sun": 1}
        for row in tag:
            a.append(remove_tags(row).split())


    def start_requests(self):
        with open('diningcodeUrlCrawler.csv') as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                # self.category = row['category']
                # self.sub_location = row['sub_location']
                yield scrapy.Request(row['url'], self.parse_rest)

    def parse_rest(self, response):
        item = DiningCodeItem()

        item['idx'] = self.idx
        print(self.idx)
        item['category'] = self.category
        item['sub_location'] = self.sub_location
        item['name'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[1]/div[2]/p').extract()[0])
        item['food'] = self.htmlparser(response.xpath('//*[@id="div_profile"]/div[1]/div[3]/a'))
        item['score'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[1]/div[4]/p/strong').extract()[0])
        item['like'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[1]/div[5]/a[1]/span/i').extract()[0])
        item['location'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[2]/ul/li[2]').extract()[0])
        item['tel'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[2]/ul/li[3]').extract()[0])
        item['tag'] = self.htmlparser(response.xpath('//*[@id="div_profile"]/div[2]/ul/li[4]/span'))
        item['type'] = self.htmlparser(response.xpath('//*[@id="div_profile"]/div[2]/ul/li[5]/span'))

        time_path = response.xpath('//*[@class="busi-hours short"]/ul/li').extract()
        if time_path:
            time_list = self.timeparser(time_path)
            item['time'] = time_list


        # item['price'] = remove_tags(response.xpath('//*[@id="div_detail"]/div[2]/ul/li[1]/p[2]').extract()[0])
        # item['star'] = remove_tags(response.xpath('//*[@id="lbl_review_point"]').extract()[0])
        # item['good_percent'] = response.xpath('').extract()[0]
        # item['bad_percent'] = response.xpath('').extract()[0]
        self.idx += 1


        time.sleep(3)

        yield item
