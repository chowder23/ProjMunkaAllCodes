using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using HtmlAgilityPack;
using ProjektMunkaAPI.Model;
using System.Text;

namespace ProjektMunkaAPI.Controllers
{
    


    [ApiController]
    [Route("[controller]")]
    public class ExchangeRateCollectorController : ControllerBase
    {
        

        private ConvertedHTML allStuff = new ConvertedHTML();
        public IEnumerable<ExchangeRates> Index(string currencyType)
        {
            ValutaValtok(currencyType);
            return allStuff.ToEnumerable();
        }

        private void ValutaValtok(string currencyType)
        {
            const int PAGENUMBER = 2;
            const int TDSTARTNUMBER = 8;
            const int TDJUMPNUMBER = 7;
            const int TDENDNUMBER = 145;
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
                    allStuff.addNewExchangeRate(nodes[i].InnerText, nodes[i + 3].InnerText, nodes[i + 2].InnerText, currencyType, "HUF");
                }
            }
        }
    }
}