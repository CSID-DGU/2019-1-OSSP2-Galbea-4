# 2019-1-OSSP2-Galbea-4

Android Studio를 이용해서 App Front 개발

Firebase의 Firestore, function을 이용한 DB, 서버 구축

Scrapy, Selenium을 이용한 DB 생성 및 크롤링

Tmap Api를 이용한 추천 장소까지 거리 및 네이게이션 생성

###팀원

<pre>
안중환 2015112158 (an004005@gmail.com)   - 서버 및 DB 구현

김재엽 2015112152 (ceo05172@naver.com)   - 서버 및 DB 구현 

백수현 2011112359 (sky2alfory@naver.com) - 지도UI및 경로찾기구현 

이진오 2015112186 (ljo0856@naver.com)    - 앱 프론트 구현
</pre>

### 개발 환경

Android Studio(Java), Firebase(Firestore, functions), node js

OS : Windows10

### 실행환경

* 가상머신 : Android7.0-API24 및 Android9.0-API28
* 휴대폰 : Galaxy Note 9
* OS : windows10 컴파일 확인

### 설치하기

<pre>
$ git clone https://github.com/CSID-DGU/2019-1-OSSP2-Galbea-4
</pre>


* crawler.zip - 크롤러 파일(DB생성)
<pre>
$ pip install scrapy
$ pip install selenium
$ scrapy crawl diningCodeUrlCrawler
$ scrapy crawl diningCodeCrawler
</pre>


* DBuploader.zip - Firestore DB import
자신의 firebase URL로 값 변경 및 service key설정
<pre>
$ npm install firebase-admin
$ node "Your_Project_Folder_Name"
</pre>
[Reference_Site](https://medium.com/@impaachu/how-to-upload-data-to-firebase-firestore-cloud-database-63543d7b34c5)


* functions.zip - Firebase의 functions파일

<pre>
$ firebase deploy --only functions
</pre>


### 실행화면
* galbea_final.pptx 참조


### 참고 사이트

DBuploader - https://medium.com/@impaachu/how-to-upload-data-to-firebase-firestore-cloud-database-63543d7b34c5

Tmap Api - http://tmapapi.sktelecom.com

Crawler - https://excelsior-cjh.tistory.com/86

### 기타 문의

* 안중환 - an004005@gmail.com