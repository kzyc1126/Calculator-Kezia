package com.calculator.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var input:Double  = 0.0
    var result:Double = 0.0
    var operator:Boolean = false
    var equal:Boolean = false
    var currentoperator:String = "none"
    var clickedop :String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttoninit()
    }
    fun buttoninit(){
        btn_0.setOnClickListener(){
            numbpressed(btn_0.text.toString())
        }
        btn_1.setOnClickListener(){
            numbpressed(btn_1.text.toString())
        }
        btn_2.setOnClickListener(){
            numbpressed(btn_2.text.toString())
        }
        btn_3.setOnClickListener(){
            numbpressed(btn_3.text.toString())
        }
        btn_4.setOnClickListener(){
            numbpressed(btn_4.text.toString())
        }
        btn_5.setOnClickListener(){
            numbpressed(btn_5.text.toString())
        }
        btn_6.setOnClickListener(){
            numbpressed(btn_6.text.toString())
        }
        btn_7.setOnClickListener(){
            numbpressed(btn_7.text.toString())
        }
        btn_8.setOnClickListener(){
            numbpressed(btn_8.text.toString())
        }
        btn_9.setOnClickListener(){
            numbpressed(btn_9.text.toString())
        }
        btn_round.setOnClickListener(){
            simpleoprtpressed("round")
        }
        btn_floor.setOnClickListener(){
            simpleoprtpressed("floor")
        }
        btn_ceil.setOnClickListener(){
            simpleoprtpressed("ceil")
        }
        btn_power.setOnClickListener(){
            simpleoprtpressed("^")
        }
        btn_sqrt.setOnClickListener(){
            simpleoprtpressed("sqrt")
        }
        btn_clear.setOnClickListener(){
            if(btn_clear.text == "AC"){
                tv_result.setText("0")
                input = 0.0
                result = 0.0
                operator= false
                equal= false
                currentoperator= "none"
                clickedop= ""
            }else if(btn_clear.text == "C"){
                btn_clear.text = "AC"
                tv_result.text = "0"
                operator = false
            }
        }
        btn_plus.setOnClickListener(){
            clickedop += "+"
            baseoprtpressed(clickedop)
        }
        btn_time.setOnClickListener(){
            clickedop += "x"
            baseoprtpressed(clickedop)
        }
        btn_min.setOnClickListener(){
            clickedop += "-"
            baseoprtpressed(clickedop)
        }
        btn_div.setOnClickListener(){
            clickedop += "/"
            baseoprtpressed(clickedop)
        }

        btn_point.setOnClickListener(){
            if( "." !in tv_result.text.toString()){
                if(tv_result.text.toString().toDouble() == result){
                    tv_result.text = "0."
                }
                else{
                    tv_result.text = tv_result.text.toString() +  "."
                }

            }
        }
        btn_equal.setOnClickListener(){
            equal = true
            if(currentoperator == "^" || currentoperator == "sqrt"){
                trailing0(calculate(result,input,currentoperator).toString())
            }
            else if(currentoperator != "none" && operator == false){
                input = tv_result.text.toString().toDouble()
                calculate(result,input,currentoperator)
                trailing0(result.toString())
                operator = true
            }
            else if (currentoperator !="none" && operator == true){
                calculate(result,input,currentoperator)
                trailing0(result.toString())
            }
            else if(currentoperator == "none" && operator == false){
                result = tv_result.text.toString().toDouble()
                trailing0(result.toString())
            }


        }
        btn_percentage.setOnClickListener(){
            simpleoprtpressed("%")
        }
        btn_bckspace.setOnClickListener(){
            if(equal == false && tv_result.text.toString().toDouble() != result && tv_result.text.toString().length > 1){
                 tv_result.text =tv_result.text.dropLast(1)
            }
            else if(equal == false  && tv_result.text.toString().length == 1){
                tv_result.text = "0"
            }
        }
    }
    fun numbpressed(inputnum:String){
        btn_clear.text = "C"
        clickedop=""
        if(tv_result.text.toString().length <9) {
            if ( operator == false){
                if(inputnum != "0" && tv_result.text == "-0"){
                    input = inputnum.toDouble() * -1
                    trailing0(input.toString())

                }
               else if ((inputnum != "0" && tv_result.text == "0") || (inputnum != "0" && equal == false && tv_result.text == "0" )) {
                    tv_result.text = inputnum
                }

                else if ((inputnum == "0" && tv_result.text !== "0") || (inputnum != "0" && tv_result.text !== "0")|| (inputnum != "0" && "." in tv_result.text.toString())) {
                    tv_result.text = tv_result.text.toString() + inputnum

                }

            }
            else if(operator == true){
                operator = false
                    tv_result.text = inputnum
            }

        }

    }
    fun simpleoprtpressed(inputop:String){
        var input2 = tv_result.text.toString().toDouble()
        when(inputop){
            "^" -> {
                if(equal == false && result == 0.0){
                    result =  Math.pow(input2,2.0)
                    trailing0(result.toString())
                    currentoperator = "^"
                }
                else if ((equal == false && result != 0.0) || (equal != false && result != 0.0)){
                    operator = false
                    input = Math.pow(input2,2.0)
                    trailing0(input.toString())
                }

            }
            "sqrt" -> {
                if(equal == false && result == 0.0){
                    result =  Math.sqrt(input2)
                    trailing0(result.toString())
                    currentoperator = "sqrt"
                }
                else if (equal == false && result != 0.0 || (equal != false && result != 0.0)){
                    operator = false
                    input = Math.sqrt(input2).toDouble()
                    trailing0(input.toString())
                }


            }
            "round" -> {
                operator = true
                result = Math.round(input2).toDouble()
                trailing0(result.toString())
            }
            "floor" -> {
                operator = true
                result = Math.floor(input2).toDouble()
                trailing0(result.toString())
            }
            "ceil" -> {result = Math.ceil(input2).toDouble()
                trailing0(result.toString())
            }

            "%"->{
                operator = true
               if(currentoperator == "x" || currentoperator == "/"){
                   input = input2/100
                   trailing0(input.toString())
               }
               else  if(input == 0.0 && input2 != 0.0) {
                   input = input2/100
                   trailing0(input.toString())
               }
               else{
                   if(input != 0.0 && input2 != 0.0){
                    input = input * input2 /100
                    trailing0(input.toString())
                }
              }


            }
        }



    }
    fun baseoprtpressed(baseop: String){
        if(operator == true){
            currentoperator = baseop.takeLast(1)

    }
        else{
            if(equal != true) {
                input = tv_result.text.toString().toDouble()
                if (currentoperator.toString() == "none" ){
                    result = input
                    currentoperator = baseop
                }
                else if(currentoperator.toString() != "none" && baseop.length <= 1){
                    calculate(result,input,currentoperator)
                    currentoperator = baseop
                }
                trailing0(result.toString())
                operator = true
            }
            else{
                currentoperator = baseop
                operator = true
            }
        }

    }

    fun calculate(result : Double,input: Double,oprt:String): Double {
        when(oprt){
            "+" -> this.result += input
            "-" -> this.result -= input
            "x" -> this.result *= input
            "/" -> this.result /= input
            "^" -> this.result = Math.pow(result,2.0)
            "sqrt" -> this.result = Math.sqrt(result)
        }
        return this.result
    }
    fun trailing0(input:String){
        if (input.takeLast(2).toString() == ".0" ){
            tv_result.text = input.replace(".0","")
        }
        else {
            tv_result.text = input
        }

    }
}

