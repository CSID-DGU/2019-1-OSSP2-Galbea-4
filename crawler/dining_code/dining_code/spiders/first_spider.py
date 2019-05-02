import scrapy
import re
import json
import csv
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
from dining_code.items import DiningCodeItem
from scrapy.utils.markup import remove_tags
from scrapy.selector import Selector


def getTime():
    1


class DiningCodURLSpider(scrapy.Spider):  # jMap[0]['lat']  jMap[0]['lng']
    name = "diningCodeUrlCrawler"
    allowed_domains = ['diningcode.com']
    start_urls = ["https://www.diningcode.com/list.php?query=%EC%84%9C%EC%9A%B8%EC%A4%91%EA%B5%AC%20%EC%9D%B4%ED%83%88%EB%A6%AC%EC%95%88"]

    def __init__(self):
        scrapy.Spider.__init__(self)
        self.browser = webdriver.Chrome("C:/Users/an004/chromedriver.exe")

    def parse(self, response):
        item = DiningCodeItem()
        self.browser.get(response.url)
        time.sleep(2)
        while True:
            try:
                self.browser.find_element_by_xpath('//*[@id="div_list_more"]/a').send_keys(Keys.ENTER)
            except:
                break
            time.sleep(5)

        response_selenium = Selector(text=self.browser.page_source)

        rows = response_selenium.xpath('//*[@id="div_list"]/li[not(@id)]')
        print(rows)
        i = -1
        for idx, row in enumerate(rows):
            if i < 2:
                i += 1
                continue
            item['idx'] = remove_tags(row.xpath('//*[@id="div_list"]/li[{}]/a/span[2]'.format(idx)).extract()[0])
            item['idx'] = re.findall("\d+", item['idx'])[0]
            item['url'] = row.xpath('//*[@id="div_list"]/li[{}]/a/@href'.format(idx)).extract()[0]
            item['url'] = "https://www.diningcode.com" + item['url']

            i += 1
            if i == 12:
                i = 0

            yield item


class DiningCodSpider(scrapy.Spider):  # jMap[0]['lat']  jMap[0]['lng']
    name = "diningCodeCrawler"

    def start_requests(self):
        with open('diningcodeUrlCrawler.csv') as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                print(row)
                yield scrapy.Request(row['url'], self.parse)

    def parse(self, response):
        item = DiningCodeItem()

        # item['idx'] = response.xpath('').extract()[0]
        item['name'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[1]/div[2]/p').extract()[0])
        item['sub_location'] = response.xpath('').extract()[0]
        # item['food'] = response.xpath('').extract()[0]
        item['score'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[1]/div[4]/p/strong').extract()[0])
        # item['star'] = remove_tags(response.xpath('//*[@id="lbl_review_point"]').extract()[0])
        item['like'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[1]/div[5]/a[1]/span/i').extract()[0])
        item['location'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[2]/ul/li[2]').extract()[0])
        item['tel'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[2]/ul/li[3]').extract()[0])
        item['tag'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[2]/ul/li[4]').extract()[0])
        item['type'] = remove_tags(response.xpath('//*[@id="div_profile"]/div[2]/ul/li[5]').extract()[0])
        # item['time'] = response.xpath('').extract()[0]
        item['price'] = remove_tags(response.xpath('//*[@id="div_detail"]/div[2]/ul/li[1]/p[2]').extract()[0])
        # item['good_percent'] = response.xpath('').extract()[0]
        # item['bad_percent'] = response.xpath('').extract()[0]


        time.sleep(5)

        yield item
