package com.it.rxapp_manager_android.module.act

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.SearchCarEntity
import com.it.rxapp_manager_android.module.adapter.CarTypeAdapter
import com.it.rxapp_manager_android.module.base.ApiConstants
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.utils.TextUtil
import com.it.rxapp_manager_android.widget.MyProgress
import com.squareup.otto.Subscribe
import javax.inject.Inject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.it.rxapp_manager_android.utils.LogUtils
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type


/**
 * Created by deqiangchen on 2018/9/29 11:16
 */
class CarTypeActivity : BaseActivity(), TextWatcher {

    private lateinit var etCar: EditText
    private lateinit var ivCancle: ImageView
    private lateinit var tvCancle: TextView
    private lateinit var lvCar: ListView
    private lateinit var adapter: CarTypeAdapter
    private lateinit var datas: ArrayList<SearchCarEntity>
    private lateinit var result: String
    private lateinit var progress: MyProgress

    companion object {
        @JvmStatic
        fun startCarTypeActivity(context: Activity) {
            context.startActivityForResult(Intent(context, CarTypeActivity::class.java), Constants.REQUEST_SELECT_CAR_ACTIVITY)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_type)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        progress = MyProgress(this)
        initView()
    }

    private fun initView() {
        etCar = findViewById(R.id.et_car) as EditText
        ivCancle = findViewById(R.id.img_cancel) as ImageView
        tvCancle = findViewById(R.id.tv_cancel) as TextView
        lvCar = findViewById(R.id.lv_car) as ListView

        tvCancle.setOnClickListener {
            finish()
        }
        ivCancle.setOnClickListener {
            etCar.text.clear()
        }

        etCar.addTextChangedListener(this)
        adapter = CarTypeAdapter(this, arrayListOf())
        lvCar.adapter = adapter

//        adapter.addAll(TextUtil.getCar(this))
        etCar.addTextChangedListener(this)
        lvCar.setOnItemClickListener { _, _, i, _ ->
            setResult(Activity.RESULT_OK, Intent().putExtra(Constants.ACTIVITY_BACK_DATA, adapter.getItem(i) as SearchCarEntity))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        progress.show()
        getCars()

    }

    private fun getCars() {
        //创建okhttp端口
        val okHttpClient = OkHttpClient()
        //创建要发送的求情（这里创建的是没有参数的GET请求）
        val request = Request.Builder().url(ApiConstants.searchCar).build()
        val call = okHttpClient.newCall(request)
        //CallBack是请求回调
        call.enqueue(object : Callback {
            //请求失败执行的方法
            override fun onFailure(call: Call, e: IOException) {
                //这里的失败指的是没有网络请求发送不出去，或者请求地址有误找不到服务器这类情况
                //如果服务器返回的是404错误也说明请求到服务器了，属于请求成功的情况，要在下面的方法中处理
            }

            @Throws(IOException::class)
            //请求成功执行的方法
            override fun onResponse(call: Call, response: Response) {
                progress.dismiss()
                result = response.body()!!.string()
                //请求成功以后的操作在这个方法里执行，并且这是个子线程，不能做更新界面的操作
                runOnUiThread {
                    //如果有更新UI的操作，需要自己写runOnUiThread这一类的方法去执行
                    adapter.addAll(TextUtil.getCars(result))
                }
            }
        })
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        datas = arrayListOf()
        if (!TextUtil.isEmpty(s.toString())) {
            TextUtil.getCars(result).forEach { it ->
                if (it.label.contains(s.toString())) {
                    datas.add(it)
                }
            }
            adapter.clear()
            runOnUiThread {
                adapter.addAll(datas)
            }
        }
    }
}