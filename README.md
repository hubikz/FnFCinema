# FnFCinema

Application allow to manage Cinema content, show times.
Customers are able to find when movie will be played and put review  

## Assumptions
1. Application is behind gateway. Gateway authenticate user getting his token (e.g. Bearer) and then add header x-user-id passing request to application.
    
    1. If we wouldn't have such gateway, we can rewrite UserValueResolver e.g. to communicate with oAuth service
    
2. We had prepared CI/CD pipeline. Where tests are run every commit.
3. Production release based on build docker container where we passing secrets, passwords etc. from environment variables (e.g. k8s secrets)
4. Application have health check endpoint

## ADR
1. Movies data are quire constant, taking in consideration IMDB API limits requests I decide to take data from there once on creation. In future "refresh" endpoint can be implemented.

## How to run tests

1. Run MongoDB first

```docker run -d --name mongo_test -p 27017:27017 --rm mongo```

