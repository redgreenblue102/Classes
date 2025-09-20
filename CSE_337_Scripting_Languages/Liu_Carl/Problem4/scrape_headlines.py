from scraper_helper import ScraperHelper

def scrape_headlines():
    # URL of the BBC website
    url = "https://www.bbc.com"
    
    # Fetch and parse the webpage
    soup = ScraperHelper.fetch_webpage(url)
    
    if soup:
        # Extract headlines and links
        # TODO: Call the extract_headlines method and pass the correct arguments
        # Example: headlines = ScraperHelper.extract_headlines(...)
        headlines = ScraperHelper.extract_headlines(soup,url,"h2","dhclWg")
        # TODO: Use the print_headlines method to display the results
        ScraperHelper.print_headlines(headlines)
    else:
        print("Failed to retrieve headlines.")

# Run the function
scrape_headlines()
