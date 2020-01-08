using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ProjektMunkaAPI2.Model
{
    public class ExchangeRates
    {
        public string ExchangerName { get; set; }
        public string Location { get; set; }
        public string BuyingRate { get; set; }
        public string SellingRate { get; set; }

        public string CurrencyFrom { get; set; }

        public string CurrencyTo { get; set; }

        public ExchangeRates(string exchangeName,string location,string buyingRate,string sellingRate,string currencyFrom,string currencyTo)
        {
            ExchangerName = exchangeName;
            Location = location;
            BuyingRate = buyingRate;
            SellingRate = sellingRate;
            CurrencyFrom = currencyFrom;
            CurrencyTo = currencyTo;
        }
    }
}
