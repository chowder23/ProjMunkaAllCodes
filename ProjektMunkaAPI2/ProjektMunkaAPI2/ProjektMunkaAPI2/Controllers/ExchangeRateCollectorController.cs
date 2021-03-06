﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using HtmlAgilityPack;
using ProjektMunkaAPI2.Model;
using System.Text;

namespace ProjektMunkaAPI2.Controllers
{
    


    [ApiController]
    [Route("api/[controller]")]
    public class ExchangeRateCollectorController : ControllerBase
    {

        [HttpGet]
        public IEnumerable<ExchangeRates> Get(string currencyType)
        {
            if (currencyType == null)
                return null;
            ConvertedHTML allStuff = new ConvertedHTML();
            const int PAGENUMBER = 2;
            const int TDSTARTNUMBER = 7;
            const int TDJUMPNUMBER = 7;
            const int TDENDNUMBER = 145;
            //http://valutavaltok.hu/valuta-arfolyamok?currentpage=0&currency_id=EUR&box_search_open=0&box_search_open_date=&box_search_city_id=392&box_search_region=
            string link = @"http://valutavaltok.hu/valuta-arfolyamok?currentpage=0&currency_id="+currencyType+@"&box_search_open=0&box_search_open_date=&box_search_city_id=392&box_search_region=";
            for (int pageIndex = 0; pageIndex < PAGENUMBER; pageIndex++)
            {
                var html = link.Replace("currentpage=0","currentpage="+pageIndex);
                HtmlWeb web = new HtmlWeb();
                web.OverrideEncoding = Encoding.UTF8;
                var htmlDoc = web.Load(html);

                var nodes = htmlDoc.DocumentNode.SelectNodes("//div/table/tr/td");


                for (int i = TDSTARTNUMBER; i < TDENDNUMBER; i += TDJUMPNUMBER)
                {
                    allStuff.addNewExchangeRate(nodes[i].InnerText,nodes[i+1].InnerText, nodes[i + 4].InnerText, nodes[i + 3].InnerText, currencyType, "HUF");
                }
            }

            return allStuff.ToEnumerable();
        }
    }
}