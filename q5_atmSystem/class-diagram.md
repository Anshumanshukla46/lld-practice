Question:
Design an ATM System

A user should be able to:

* insert card
* authenticate using PIN
* withdraw cash
* deposit cash
* check account balance

The system should also maintain transaction details and handle cases like insufficient balance or cash unavailable in ATM.

You can ignore:

* networking
* multiple ATMs
* bank backend internals
* card expiry/payment systems

--------
you helped me with given design flow 

My design:

AtmCard:
- cardNumber: string
- pin: string
- balance: int

AtmStatus: <<ENUM>>
- INVALID_PIN
- AUTHENTICATED

TxnStatus:
- FAILED
- COMPLETED

AtmService:
- atmMapping: Map<String, AtmCard>

- insertAndAuthenticate(cardNumber: string, pin: string) : AtmStatus

+ withdrawCash(cardNumber:string, pin: string, amountToWithdraw: int): TxnStatus
  - insertAndAuthenticateCard(atm)
    - getCardFromMap: Map<String, AtmCard>
    - check balance 
      - if more then withdraw 
      - else can show FAILED status or balance sufficient as custom exception

+ depositCash(cardNumber:string, pin: string, amountToWithdraw: int): TxnStatus
  - insertAndAuthenticateCard(atm)
    - getCardFromMap: Map<String, AtmCard>
      - add balance

+ checkBalance(cardNumber:string, pin: string):int
  - insertAndAuthenticateCard(atm)
    - getCardFromMap: Map<String, AtmCard>
      - return balance
      
-----

Correct Answer:

AtmCard
- cardNumber
- pin
- balance
- transactions: List<Transaction>

ATM
- totalCash

Transaction
- transactionId
- amount
- txnType
- txnStatus
- timestamp

TxnType <<ENUM>>
- WITHDRAW
- DEPOSIT
- BALANCE_CHECK

TxnStatus <<ENUM>>
- SUCCESS
- FAILED
- INVALID_PIN
- INSUFFICIENT_BALANCE
- ATM_CASH_UNAVAILABLE

ATMService
- atm: ATM
- atmMapping: Map<String, AtmCard>

+ authenticate(cardNumber, pin)

+ withdrawCash(cardNumber, pin, amount)

+ depositCash(cardNumber, pin, amount)

+ checkBalance(cardNumber, pin)