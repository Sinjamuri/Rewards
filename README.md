This is REST API application using Spring Boot.
After you start the Spring Boot application, you can call the Rest API using postman like below:
http://localhost:8080/summary  GET method
Use the following Json as request
[
  {
    "name":"ABC",
    "date":"2020-07-20",
    "amount":220
  },
  {
    "name":"XYZ",
    "date":"2020-07-10",
    "amount":133
  },
  {
    "name":"ABC",
    "date":"2020-07-22",
    "amount":116
  },
  {
    "name":"ABC",
    "date":"2020-08-18",
    "amount":120
  },
  {
    "name":"XYZ",
    "date":"2020-08-10",
    "amount":63
  },
  {
    "name":"XYZ",
    "date":"2020-08-12",
    "amount":103
  },
  {
    "name":"ABC",
    "date":"2020-09-22",
    "amount":20
  },
  {
    "name":"ABC",
    "date":"2020-09-30",
    "amount":201
  },
  {
    "name":"XYZ",
    "date":"2020-09-10",
    "amount":420
  },
  {
    "name":"XYZ",
    "date":"2020-09-11",
    "amount":328
  }
]


The output will be as shown:
[
    {
        "name": "ABC",
        "month1Total": 372,
        "month2Total": 90,
        "month3Total": 252,
        "threeMonthsTotal": 714
    },
    {
        "name": "XYZ",
        "month1Total": 116,
        "month2Total": 106,
        "month3Total": 1196,
        "threeMonthsTotal": 1418
    }
]
