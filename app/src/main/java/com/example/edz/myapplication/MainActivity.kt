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
            val intent = Intent(this@MainActivity, VariableActivity::class.java)
            startActivity(intent)
        }
        tv_control_flow.setOnClickListener {
            val intent = Intent(this@MainActivity, ControlFlowActivity::class.java)
            startActivity(intent)
        }
        tv_operator.setOnClickListener {
            val intent = Intent(this@MainActivity, OperatorActivity::class.java)
            startActivity(intent)
        }
        tv_function.setOnClickListener {
            val intent = Intent(this@MainActivity, FunctionActivity::class.java)
            startActivity(intent)
        }
        tv_extend.setOnClickListener {
            val intent = Intent(this@MainActivity, ExtendActivity::class.java)
            startActivity(intent)
        }
        tv_collection.setOnClickListener {
            val intent = Intent(this@MainActivity, CollectionActivity::class.java)
            startActivity(intent)
        }
//        tv_operator.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//        })
//        tv_operator.setOnClickListener { it ->
//            val id = it?.id }

    }
}
