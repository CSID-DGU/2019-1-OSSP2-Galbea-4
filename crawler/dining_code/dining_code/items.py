# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy


class DiningCodeItem(scrapy.Item):
    idx = scrapy.Field()  # 순위
    url = scrapy.Field()  # url
    lat = scrapy.Field()  # 위도
    lng = scrapy.Field()  # 경도
    name = scrapy.Field()  # 가게이름
    sub_location = scrapy.Field()   # 세부주소
    food = scrapy.Field()   # 음식들
    score = scrapy.Field()  # 점수
    star = scrapy.Field()   # 별점
    like = scrapy.Field()   # 좋아요
    location = scrapy.Field()   # 주소
    tel = scrapy.Field()    # 번호
    tag = scrapy.Field()    # 태그
    type = scrapy.Field()   # 유형
    start_time = scrapy.Field()   # 영업시간
    end_time = scrapy.Field()
    holiday = scrapy.Field()
    breaktime = scrapy.Field()
    price = scrapy.Field()  # 가격
    good_percent = scrapy.Field()   # 긍정적 비율
    bad_percent = scrapy.Field()    # 부정적 비율
    pass
