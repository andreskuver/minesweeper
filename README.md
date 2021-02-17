# Minesweeper-API
REST API for Minesweeper Game

This project is a simple REST API to play minesweeper game.
Documentation of the endpoints can be found [here][swagger-link].

### Challenge Progress

- [X] Design and implement a documented RESTful API for the game (think of a mobile app for your API)
- [ ] Implement an API client library for the API designed above. Ideally, in a different language, of your preference, to the one used for the API
- [X] When a cell with no adjacent mines is revealed, all adjacent squares will be revealed (and repeat)
- [X] Ability to 'flag' a cell with a question mark or red flag
- [X] Detect when game is over
- [X] Persistence
- [ ] Time tracking
- [X] Ability to start a new game and preserve/resume the old ones
- [X] Ability to select the game parameters: number of rows, columns, and mines
- [ ] Ability to support multiple users/accounts

### Architectural and design notes 

- The Application is deployed in Heroku.
- CI-CD with Travis -> Github -> Heroku. Travis CI run on every commit or `PR merge`
to `main` branch on Github, once the CI finish with successful status Heroku 
  deploys a new version
- The project uses a relational database (Postgres)
- There are Unit tests using JUnit and Integration test using SpringRunner and MockMVC
- Swagger to document the API

### How to play
There are two endpoints to play, a `POST /api/game` to start a new Game and a `PUT /api/game/{gameId}` to interact with an active game.
Explained information for each endpoint is in the [swagger page][swagger-link].

[swagger-link]: https://minesweeper-rest-api.herokuapp.com/swagger-ui.html