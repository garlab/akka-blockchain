# Akka blockchain

This is a sample project to implement a minimum blockchain with akka.
The blockchain impose a hash containing four leading zeros as proof of work.

# Dependencies

- scala@2.12.1
- sbt@0.13.13 - http://www.scala-sbt.org/download.html

## Run

    sbt run

## Tests

    sbt test

## TODO

- Restart MiningActor after timeout
- Add http API
- Add frontend in react

## Resources

- http://johnmathews.eu/blockchain-introduction.html
- https://medium.com/crypto-currently/lets-build-the-tiniest-blockchain-e70965a248b
- https://github.com/aviaviavi/legion
