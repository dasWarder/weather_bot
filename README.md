# Telegram Weather Bot

Telegram Long Polling bot that provide information about the weather. The bot is using spring boot telegram starter
and Redis  for caching requests for period of time (default is 1 hour)

### Start

* Preparation: it's necessary to start Redis before the bot will be run up.
It's possible to do manually or by Docker-compose file:
``
  $ docker-compose up -d
``
* Run: just run up it locally or deploy to a web hosting, i.e. Heroku or AWS.

### Commands

```/start``` - receive start information

```/help``` - get help

```/ru``` - select Russian language

```/eng``` - select English language

```/weather city_name``` - get current weather for a city

```/drive city_name``` - get drive weather for a city

```/forecast city_name``` - get forecast for 3 days for a city

```/forecast [amount of days] city_name``` - get forecast for N days (max 3) for a city