
fun main() {

    println("Welcome to your banking system. ")
    println("What type of account would you like to create?")
    println("1. Debit Account")
    println("2. Credit Account")
    println("3. Checking Account")

    var accountType = ""
    var userChoice = 0
    print("Choose an option: ")
    userChoice = readln().toInt()
    val aa = 12.0
    accountType = when(userChoice){
        1 -> "Debit Account"
        2 -> "Credit Account"
        3 -> "Checking Account"
        else -> "Error. No bank account created"
    }
    if (userChoice <= 3 ){
        println("You have created a $accountType")
    }else{
        println(accountType)
    }
    println()



    var isSystemOpen = true

    while (isSystemOpen){
        println("What would you like to do?\n" +
                "1. Check bank account balance\n" +
                "2. Withdraw money\n" +
                "3. Deposit money\n" +
                "4. Close the app\n" +
                "Which option do you choose? (1, 2, 3 or 4) \n")

        var option = readln().toInt()
        var mode =""
        var amount = 0
        when(option){
            1 -> println("The current balance is $accountBalance \n")
            2 -> {
                mode = "withdraw"
                println("How much would you like to withdraw?")
                amount = readln().toInt()
                transfer(accountType, mode, amount)
            }
            3 ->{
                mode = "deposit"
                println("How much would you like to deposit?")
                amount = readln().toInt()
                transfer(accountType, mode, amount)
            }
            4 ->isSystemOpen = false
        }
    }

}




fun transfer(accountType: String, mode: String, transferAmount: Int){
    when(mode){
        "withdraw" -> {
            if (accountType == "Debit Account"){
                withdrawForDebit(transferAmount)
            }else{
                withdrawForCreditAndChecking(transferAmount)
            }
        }
        "deposit" -> {
            if(accountType == "Credit Account"){
                depositForCredit(transferAmount)
            }else{
                depositForDebitAndChecking(transferAmount)
            }
        }
        else -> {return}
    }
}

var accountBalance = (0..1000).random()


fun withdrawForCreditAndChecking(amount: Int): Int{

    println("The amount you withdrew is $amount \n")
    accountBalance -= amount
    println("Your current balance is ${accountBalance} \n")
    return amount //amount withdrawn

}

fun withdrawForDebit(amount: Int): Int{
    if(accountBalance == 0){
        println("Can't withdraw, no money on this account! \n")
        return accountBalance
    }else if(amount > accountBalance){
        println("Not enough money on this account! The checking balance is ${accountBalance} dollars. \n")
        return 0 //no money withdrawn
    }
    println("The amount you withdrew is $amount \n")
    accountBalance -= amount
    println("Your current balance is ${accountBalance} \n")
    return amount

}

fun depositForDebitAndChecking(amount:Int): Int{
    println("The amount you deposited is $amount \n")
    accountBalance += amount
    println("Your current balance is ${accountBalance} \n")
    return amount //amount deposited
}

fun depositForCredit(amount: Int): Int{
    if (accountBalance == 0){
        println("You don't need to deposit anything in order to pay off the account since it has already been paid off \n")
        return accountBalance
    }else if (accountBalance + amount > 0){
        println("Deposit failed, you tried to pay off an amount grater than the credit balance. The checking balance is ${accountBalance} dollars \n")
        return 0
    //}else if (amount == -accountBalance){
    }else if (accountBalance < 0){
        accountBalance += amount
        println("You have paid $amount to your credit account \n")
        if (accountBalance == 0 ){
            println("You have paid off this account! \n")
        }
        println("Your current balance is $accountBalance \n")

        return amount
    }
    depositForCredit(amount)
    return amount
}

