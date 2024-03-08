import tweepy
import Math

access_token = '1765406326190956544-NhagkIzDxnMWx4qgOrmAmZbnvOAPfE'
access_secret = 'N8lQpHZbH6isNCUl8Hg20he6hY0CUaqt9m49u7OhIn3ti'

consumer_key = 'bGF1YUdzQWJuelhQck9tbHdYbWc6MTpjaQ'
consumer_secret = 'FV55bUmBS2Xaq-XKINVh_FvQLS9kbmFh7i_KpY50YmC8CrGMIG'

client = tweepy.Client(access_token=access_token, access_token_secret=access_secret, consumer_key=consumer_key, consumer_secret=consumer_secret)
response = client.create_tweet(text="Tweet Successful")