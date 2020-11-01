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
                result = 0.0
                tv_result.setText("0")
                currentoperator = "none"
                equal = false
                operator = false
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
        btn_plusmin.setOnClickListener(){
            negpointpressed("+-")
        }
        btn_point.setOnClickListener(){
            negpointpressed(".")
        }
        btn_equal.setOnClickListener(){
            equal = true
            if(currentoperator != "none" && operator == false){
                input = tv_result.text.toString().toDouble()
                tv_result.text = calculate(result,input,currentoperator).toString().replace(".0","")
                operator = true
            } else if (currentoperator !="none" && operator == true){
                tv_result.text = calculate(result,input,currentoperator).toString().replace(".0","")
            }

        }
        btn_percentage.setOnClickListener(){}
        simpleoprtpressed("%")
    }
    fun numbpressed(inputnum:String){
        btn_clear.text = "C"
        clickedop=""
        if(tv_result.text.toString().length <9) {
            if ( operator == false){
                if ((inputnum != "0" && tv_result.text == "0") || (inputnum != "0" && equal == false && tv_result.text == "0" )) {
                    tv_result.text = inputnum
                }
                else if(inputnum != "0" && tv_result.text == "-0"){
                    input = inputnum.toDouble() * -1
                    tv_result.text = input.toString().replace(".0","")

                }
                else if ((inputnum == "0" && tv_result.text !== "0") || (inputnum != "0" && tv_result.text !== "0")) {
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
        input = tv_result.text.toString().toDouble()
        when(inputop){
            "^" -> {
                if(inputop == "^") if(equal == false){
                    input = Math.pow(input,2.0)
                    tv_result.text = input.toString().replace(".0","")
                }
                else { result = Math.pow(input,2.0)
                    tv_result.text = result.toString().replace(".0","")
                }
            }
            "round" -> {
                result = Math.round(input).toDouble()
                tv_result.text = result.toString().replace(".0","")
            }
            "floor" -> {result = Math.floor(input).toDouble()
                tv_result.text = result.toString().replace(".0","")
            }
            "ceil" -> {result = Math.ceil(input).toDouble()
                tv_result.text = result.toString().replace(".0","")
            }
            "sqrt" -> {result = Math.sqrt(input).toDouble()
                tv_result.text = result.toString().replace(".0","")
            }
        }


        operator = true
    }
    fun baseoprtpressed(baseop: String){
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
            tv_result.text = result.toString().replace(".0","")
            operator = true
        } else{
            currentoperator = baseop
            equal = false
        }
    }
    fun negpointpressed(symbol:String){
        input = tv_result.text.toString().toDouble()
        if(symbol =="." && "." !in tv_result.text.toString()){
            tv_result.text = tv_result.text.toString() +  "."
        }
        else if(symbol =="+-"){
            if(result != 0.0 && input > 0.0){
                input *= -1
                tv_result.text = input.toString().replace(".0","")
            }
            else{
                result *=1
                tv_result.text = result.toString().replace(".0","")
            }



        }
    }
    fun calculate(result : Double,input: Double,oprt:String): Double {
        when(oprt){
            "+" -> this.result += input
            "-" -> this.result -= input
            "x" -> this.result *= input
            "/" -> this.result /= input
        }
        return this.result
    }
}