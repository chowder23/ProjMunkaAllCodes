using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ProjektMunkaAPI2.Model;
using System.Text.RegularExpressions;

namespace ProjektMunkaAPI2.Model
{
    public class ConvertedHTML
    {
        List<ExchangeRates> allRates = new List<ExchangeRates>();

        public void addNewExchangeRate(string exchangerName,string location, string buyingRate, string sellingRate, string currencyFrom, string currencyTo)
        {
            allRates.Add(new ExchangeRates(NormalizeString(exchangerName),NormalizeString(location),NormalizeString( buyingRate),NormalizeString( sellingRate),NormalizeString( currencyFrom),NormalizeString( currencyTo)));
        }

        private string NormalizeString(string inputString)
        {
            string outputstring = Regex.Replace(inputString, @"\n|\r|\t|\x20|\.", "");

            return outputstring;      
        }


        public IEnumerable<ExchangeRates> ToEnumerable()
        {
            return allRates;
        }
        public override string ToString()
        {
            string output = allRates[0].CurrencyFrom + " --> " + allRates[0].CurrencyTo + "\n";
            string address;
            foreach (var item in allRates)
            {
                address = Regex.Replace(item.Location, " ", "");
                address = Regex.Replace(address, "\n|\t|\r", "");
                output += address + ", Buy: " + item.BuyingRate.Trim() + ", Sell:" + item.SellingRate.Trim() + "\n";
            }

            return output;
        }
   
    }
}
