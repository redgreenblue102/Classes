# %%
import seaborn as sb #importing necessary packages for working with the data
import pandas as pd
import numpy as np
import matplotlib as mpl
import matplotlib.pyplot as plt

# %%
pd.set_option("display.max_rows",100)

# %% [markdown]
# # Step 1: Importing Data

# %%
data = pd.read_csv('AB_NYC_2019.csv') # import data from csv to python using pandas

# %% [markdown]
# # Step 2: Cleaning Data (Question 1)
# 
# Missing names for listing and name of host.
# 
# Replaced with listing id and host id respectively.
# 
# Missing values for reviews per month because 0 is not available. Thus replaced with 0.

# %%
data.info()

# %%
ndata = data.isnull()

# %%
for i,j in enumerate(ndata['name']): #replacing null name with id
    if(j==True):
        data.loc[i,'name'] = str(data.loc[i,'id'])

for i,j in enumerate(ndata['host_name']): #replacing null host name with host id
    if(j==True):
        data.loc[i,'host_name'] = str(data.loc[i,'host_id']) 
for i,j in enumerate(ndata['reviews_per_month']): # replacing null reviews per month with 0
    if(j==True):
        data.loc[i,'reviews_per_month'] = 0

# %%
data.info()

# %% [markdown]
# # Step 3: Neighborhood Pricing Trends (Question 2)
# 
# 

# %%
ntc = dict() #total price of neighbourhood and number of listings in that neighbourhood [totalprice, count, mean,neighbourhood group]
for i,j in enumerate(data['neighbourhood']):
    if not(j in  ntc):
        ntc[j] = [0,0,0,0]
    ntc[j] = [ntc[j][0]+data.loc[i,'price'],ntc[j][1]+1,0,data.loc[i,'neighbourhood_group']]

# %%
for i in ntc:
    ntc[i] = [ntc[i][0],ntc[i][1],float(ntc[i][0])/float(ntc[i][1]),ntc[i][3]]

# %%
neighbourhooddata=pd.DataFrame(ntc.values(), index=ntc.keys())
neighbourhooddata.columns = ["Total Price", "# of Listings", "Mean Price", "Neighbourhood Group"]

# %%
sortedbymean=neighbourhooddata[neighbourhooddata['# of Listings'] > 5].sort_values(by=['Mean Price']) 
sortedbymean
#sort by mean price neighbourhoods with more than 5 listings

# %% [markdown]
# 
# The top 5 most expensive neighborhoods are Tribeca, Sea Gate, Riverdale, Battery Park City, and Flatiron District
# 
# The top 5 least expensive neighborhoods are Bronxdale, Soundview, Tremont, Hunts Point, and Bull's Head

# %%
ngm = dict() # mean pricing per neighbourhood_group
for i,j in enumerate(data['neighbourhood_group']):
    if not(j in  ngm):
        ngm[j] = [0,0,0]
    ngm[j] = [ngm[j][0]+data.loc[i,'price'],ngm[j][1]+1,0]

# %%
for i in ngm:
    ngm[i] = [ngm[i][0],ngm[i][1],float(ngm[i][0])/ngm[i][1]]
plt.bar(ngm.keys(),[i[2] for i in ngm.values()])

# %% [markdown]
# Highest Mean Price of Listing is in Manhattan, while lowest mean price of listing is in the Bronx

# %% [markdown]
# # Step 4: Pearson Analysis (Question 3)
# 
# Selecting Features: Latitude, Longitude, Price, Reviews Per Month, Availability_365

# %%
def pearson(a,b): # function for calculating pearson
    mean_a = sum(a)/float(len(a))
    mean_b = sum(b)/float(len(b))
    cov = sum([(i-mean_a)*(j-mean_b)for i,j in zip(a,b)])
    stda = sum([(i-mean_a)**2 for i in a])**0.5
    stdb = sum([(j-mean_b)**2 for j in b])**0.5
    return cov/(stda*stdb)

# %%
features = ["latitude","longitude","price","reviews_per_month","availability_365"]
pearsonmat = []
max=[-10,"",""]
min=[10,"",""]
for i in features: # finding pearson correlations for each pair of features
    pearsonrow = []
    for j in features:
        p =pearson(data[i],data[j])
        pearsonrow.append(p)
        if i!=j:
            max = [p,i,j] if p> max[0] else max # maximum and minimum correlations
            min = [p,i,j] if p< min[0] else min 
        
    pearsonmat.append(pearsonrow)
print(pearsonmat)
print(max)
print(min)
sb.heatmap(pearsonmat,xticklabels=features,yticklabels=features) # mapping correlation onto a heat map

# %% [markdown]
# Largest correlation is between reviews per month and availability 365, at 0.1637
# 
# Largest negative correlation is between longitude and price at -0.15

# %% [markdown]
# # Step 5 Coordinate Trends (Question 4)
# 
# Mapping neighbourhood group of listings and pricing of listings using color

# %%
sb.scatterplot(data = data,x="longitude",y="latitude",hue='neighbourhood_group',s=3)
#color map of the listings in each neghbourhood_group

# %%
under1k=data[data['price'] < 1000]
sb.scatterplot(data = under1k,x="longitude",y="latitude",hue='price',palette='flare',s=3)

# %% [markdown]
# We see that the bottom left side of manhattan is the most expensive, and there is a gradient outwards from there

# %% [markdown]
# # Step 6: Word Cloud (Question 5)
# 
# Word cloud of words used in Airbnb names

# %%
from wordcloud import WordCloud, STOPWORDS, ImageColorGenerator
text = ""
for name in data["name"]:
    text = text+" " + name
wordcloud = WordCloud(width=720, height=720).generate(text)
plt.imshow(wordcloud, interpolation='bilinear')


# %% [markdown]
# # Step 7: Busiest Areas (Question 6)
# 
# Finding the areas with the busiest hosts which is defined as having a high number of listings.

# %%
nhl = dict() #total price of neighbourhood and number of listings in that neighbourhood [totalprice, count, mean,neighbourhood group]
for i,j in enumerate(data['neighbourhood']):
    if not(j in  nhl):
        nhl[j] = [0,0,0,0]
    nhl[j] = [nhl[j][0]+data.loc[i,'calculated_host_listings_count'],nhl[j][1]+1,0,data.loc[i,'neighbourhood_group']]

# %%
for i in nhl: # Finding mean number of listings of each host in a neighbourhood
    nhl[i] = [nhl[i][0],ntc[i][1],float(nhl[i][0])/float(nhl[i][1]),nhl[i][3]]

nhldataframe=pd.DataFrame(nhl.values(), index=nhl.keys())
nhldataframe.columns = ["Total Number of Listings", "# of Hosts", "Mean Listings", "Neighbourhood Group"]

# %%
nhldataframe[nhldataframe["# of Hosts"] > 5].sort_values(by="Mean Listings") 

# %% [markdown]
# Considering only neighbourhoods with at least 5 listings, We Have Tribeca having the highest mean number of listings at 490.64 per host.

# %%
plt.scatter(data['longitude'],data['calculated_host_listings_count'])
plt.xlabel('longitude')
plt.ylabel('calculated_host_listings_count')

# %% [markdown]
# We see above that near the center in longitude is where hosts are the busiest.

# %%
plt.scatter(data['price'],data['calculated_host_listings_count'])
print("pearson correlation:" + str(pearson(data['price'],data['calculated_host_listings_count'])))
plt.xlabel('price')
plt.ylabel('calculated_host_listings_count')

# %% [markdown]
# We see above that hosts with a lot of listings are more likely to be at the bottom part of the price range.

# %%
plt.scatter(data['number_of_reviews'],data['calculated_host_listings_count'])
print("pearson correlation: " + str(pearson(data['number_of_reviews'],data['calculated_host_listings_count'])))
plt.xlabel('number_of_reviews')
plt.ylabel('calculated_host_listings_count')

# %% [markdown]
# We see above that hosts with a lot of listings don't have that many reviews for each of their listings

# %%
plt.scatter(data['availability_365'],data['calculated_host_listings_count'])
print("pearson correlation: " + str(pearson(data['availability_365'],data['calculated_host_listings_count'])))
plt.xlabel('availability_365')
plt.ylabel('calculated_host_listings_count')

# %% [markdown]
# Through the pearson correlation of 0.226, we may conclude that the longer the availability, the more listings the host has.

# %% [markdown]
# From the above graphs we can conclude that the hosts with a lot of listings are at the bottom of the price range. We can also conclude that hosts with a lot of listings have a longitude that is at the center of manhattan. The financial district has a longitude of -74.01, which is near the center of manhattan and also has a mean price per listing of 225.50 which is near the bottom of the price range. This is why the financial district has the busiest hosts.

# %% [markdown]
# # Step 8: Interesting Plots (Question 7)

# %%
print("pearson correlation: " + str(pearson(data['availability_365'],data['reviews_per_month'])))
plt.scatter(data['availability_365'],data['reviews_per_month'])
plt.xlabel('availability_365')
plt.ylabel('reviews_per_month')


# %% [markdown]
# The above plot is that of reviews per month vs availability. There is a pearson correlation of 0.163, however it is interesting that this correlation isn't stronger. One would think that the longer the availability, the more reviews it should have due to more bookings.

# %%
sb.scatterplot(data = data,x="longitude",y="latitude",hue='room_type',s=3)
#color map of the room type

# %% [markdown]
# This is a color map of room type for each listing. We see that in Manhattan, most rooms are Entire home/apt, whereas as you move further out from manhattan, private rooms begin taking over.


