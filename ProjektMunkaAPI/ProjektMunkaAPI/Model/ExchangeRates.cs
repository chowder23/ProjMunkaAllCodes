using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ProjektMunkaAPI.Model
{
    public class ExchangeRates
    {

        public string Location { get; set; }
        public string BuyingRate { get; set; }
        public string SellingRate { get; set; }

        public string CurrencyFrom { get; set; }

        public string CurrencyTo { get; set; }

        public ExchangeRates(string location,string buyingRate,string sellingRate,string currencyFrom,string currencyTo)
        {
            Location = location;
            BuyingRate = buyingRate;
            SellingRate = sellingRate;
            CurrencyFrom = currencyFrom;
            CurrencyTo = currencyTo;
        }
    }
}
