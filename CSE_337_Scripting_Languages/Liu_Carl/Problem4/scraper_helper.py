import requests
from bs4 import BeautifulSoup
import re
class ScraperHelper:
    @staticmethod
    def fetch_webpage(url):
        """
        Fetches the content of a webpage.

        Args:
            url (str): The URL of the webpage to fetch.

        Returns:
            BeautifulSoup: A BeautifulSoup object for the webpage content if successful, None otherwise.
        """
        try:
            response = requests.get(url)
            if response.status_code == 200:
                print(f"Successfully fetched webpage: {url}")
                return BeautifulSoup(response.text, 'html.parser')
            else:
                print(f"Failed to fetch webpage. HTTP Status Code: {response.status_code}")
                return None
        except requests.RequestException as e:
            print(f"Error occurred while fetching webpage: {e}")
            return None

    @staticmethod
    def format_url(base_url, link):
        """
        Converts a relative URL to an absolute URL.

        Args:
            base_url (str): The base URL of the website.
            link (str): The relative or absolute URL.

        Returns:
            str: A complete absolute URL.
        """
        # TODO: Students will implement this method
        if(link[:len(base_url)] == base_url):
            return link
        else :
            return base_url+link

    @staticmethod
    def extract_headlines(soup, base_url, tag, class_name):
        """
        Extracts headlines and their corresponding links from the webpage.

        Args:
            soup (BeautifulSoup): A BeautifulSoup object containing the parsed HTML.
            base_url (str): The base URL for resolving relative links.
            tag (str): The HTML tag that contains headlines (e.g., 'a').
            class_name (str): The class name of the HTML elements containing headlines.

        Returns:
            list: A list of tuples, each containing a headline text and a full URL link.
        """
        # TODO: Students will implement this method
        cards = []
        links =[]
        card_headline = soup.findAll(tag,{"class": class_name})
        card_link = soup.findAll("a",{"class": "sc-2e6baa30-0"},href=True)
        for link in card_link:
            try:
                print(link['href'])
                if(re.search(".*articles.*", link['href'])):
                    links.append(link['href'])
                    
            except KeyError:
                continue
        for headline,link in zip(card_headline,links):
            try:
                headline_name = headline.get_text(strip=True)
                link_name= link
                link_name = ScraperHelper.format_url(base_url,link_name)
                cards.append((headline_name,link_name))
            except:
                continue
        return cards


    @staticmethod
    def print_headlines(headlines):
        """
        Prints the headlines and their corresponding links in a clean format.

        Args:
            headlines (list): A list of tuples, each containing (headline_text, headline_link).
        """
        if not headlines:
            print("No headlines found.")
            return

        print("\nExtracted Headlines:")
        print("-" * 40)
        for i, (headline, link) in enumerate(headlines, start=1):
            print(f"{i}. {headline}\n   Link: {link}")
        print("-" * 40)
