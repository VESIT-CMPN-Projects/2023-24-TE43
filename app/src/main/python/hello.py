import tweepy

access_token = '1765406326190956544-zwGEUqCwjfjbX9xNHDobWwnEDT3V0c'
access_secret = 'k30q2ojCi03gyVnQg6RELYIC9S7Pq51dp9spv3rGZWrVr'

consumer_key = '8eFaLRqzb98OHeTBzW7e67sT8'
consumer_secret = 'qPbSSIaCNbU3vy2XuRElFHqNr8dxgEhGIe31DLJCgVf3dUgl72'

client = tweepy.Client(consumer_key=consumer_key, consumer_secret=consumer_secret, access_token=access_token, access_token_secret=access_secret)
response = client.create_tweet(text="Event 2 on Thursday")