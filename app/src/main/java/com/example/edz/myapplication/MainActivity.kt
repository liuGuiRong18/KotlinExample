package com.example.edz.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_val.setOnClickListener {
            val intent = Intent(this, VariableActivity::class.java)
            startActivity(intent)
        }
        tv_control_flow.setOnClickListener {
            val intent = Intent(this, ControlFlowActivity::class.java)
            startActivity(intent)
        }
        tv_operator.setOnClickListener {
            val intent = Intent(this, OperatorActivity::class.java)
            startActivity(intent)
        }
    }
}
