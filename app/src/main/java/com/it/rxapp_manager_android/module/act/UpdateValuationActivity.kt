package com.it.rxapp_manager_android.module.act

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.ListValuationsEntity
import com.it.rxapp_manager_android.modle.UpdateValuationEntity
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.module.base.data.UserInfoPreferences
import com.it.rxapp_manager_android.utils.Constants
import com.it.rxapp_manager_android.utils.LogUtils
import com.it.rxapp_manager_android.utils.TextUtil
import com.it.rxapp_manager_android.widget.MyProgress
import com.it.rxapp_manager_android.widget.ShowToast
import com.it.rxapp_manager_android.widget.TimePicker
import com.squareup.otto.Subscribe
import javax.inject.Inject

/**
 * Created by deqiangchen on 2018/10/31 16:21
 */
class UpdateValuationActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var tvSelectProduct: TextView
    private lateinit var etStartPrice: EditText
    private lateinit var etStartKm: EditText
    private lateinit var etOutStartKmPrice: EditText
    private lateinit var etLongDistanceKm: EditText
    private lateinit var etLongDistanceKmPrice: EditText
    private lateinit var etSuperLongDistanceKm: EditText
    private lateinit var etSuperLongDistanceKmPrice: EditText
    private lateinit var etMaxdistancekm: EditText
    private lateinit var etMaxdistancekmPrice: EditText
    private lateinit var etOtherPrice: EditText
    private lateinit var etNightFee: EditText
    private lateinit var tvNightBegin: TextView
    private lateinit var tvNightEnd: TextView
    private lateinit var btnSave: Button
    private lateinit var spinnerNightBegin: Spinner
    private lateinit var spinnerNightEnd: Spinner
    private lateinit var spinnerMondayRate: Spinner
    private lateinit var spinnerTuesdayRate: Spinner
    private lateinit var spinnerWednesdayRate: Spinner
    private lateinit var spinnerThursdayRate: Spinner
    private lateinit var spinnerFridayRate: Spinner
    private lateinit var spinnerSaturdayRate: Spinner
    private lateinit var spinnerSundayRate: Spinner
    private lateinit var spinnerPublicholidaysRate: Spinner

    lateinit var longDistanceKm: String
    lateinit var superLongDistanceKm: String
    lateinit var mondayRat: String
    lateinit var tuesdayRat: String
    lateinit var wednesdayRat: String
    lateinit var thursdayRat: String
    lateinit var fridayRat: String
    lateinit var saturdayRat: String
    lateinit var sundayRat: String
    lateinit var publicholidaysRat: String


    @Inject
    lateinit var presenter: MyPresenter
    private lateinit var progress: MyProgress
    lateinit var userNo: String
    lateinit var userName: String
    lateinit var fluct: Array<String>
    lateinit var nightBegin: Array<String>
    lateinit var nightEnd: Array<String>
    private lateinit var data: ListValuationsEntity.PriceRulesBean

    private lateinit var timeBeginPicker: TimePicker
    private lateinit var timeEndPicker: TimePicker

    companion object {
        @JvmStatic
        fun startUpdateValuationActivity(context: Activity, userNo: String, data: ListValuationsEntity.PriceRulesBean) {
            context.startActivity(Intent(context, UpdateValuationActivity::class.java).putExtra(Constants.USER_NO, userNo).putExtra(Constants.PRODUCT, data))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_valuation)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        timeBeginPicker = TimePicker(this)
        timeEndPicker = TimePicker(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
        userName = UserInfoPreferences.getInstance().mobile
        data = intent.getSerializableExtra(Constants.PRODUCT) as ListValuationsEntity.PriceRulesBean
        initView()
        initData(data)
    }

    private fun initData(data: ListValuationsEntity.PriceRulesBean) {
        tvSelectProduct.text = "[" + data.linetypename + "]" + data.startcityname + "--" + data.endcityname + "--" + data.productType + "--" + data.cartypename
        etStartPrice.setText(data.startPrice)
        etStartKm.setText(data.startKm)
        etOutStartKmPrice.setText(data.outStartKmPrice)
        etLongDistanceKm.setText(data.longDistanceKm)
        etLongDistanceKmPrice.setText(data.longDistanceKmPrice)
        etSuperLongDistanceKm.setText(data.superLongDistanceKm)
        etSuperLongDistanceKmPrice.setText(data.superLongDistanceKmPrice)
        etMaxdistancekm.setText(data.maxdistancekm)
        etMaxdistancekmPrice.setText(data.maxdistancekmprice)
        etOtherPrice.setText(data.otherPrice)
        etNightFee.setText(data.nightFee)

        tvNightBegin.text = data.nightBegin
        tvNightEnd.text = data.nightEnd

//        nightBegin = resources.getStringArray(R.array.nightBegin)
//        for (i in nightBegin.indices) {
//            if (data.nightBegin.equals(nightBegin[i])) {
//                spinnerNightBegin.setSelection(i)
//            }
//        }
//        nightEnd = resources.getStringArray(R.array.nightEnd)
//        for (i in nightEnd.indices) {
//            if (data.nightEnd.equals(nightEnd[i])) {
//                spinnerNightEnd.setSelection(i)
//            }
//        }

        fluct = resources.getStringArray(R.array.fluctuation)
        for (i in fluct.indices) {
            val flucts = (fluct[i].substring(0, fluct[i].length - 1).toFloat() + 100).div(100).toString()
            if (data.mondayRate == "1") {
                spinnerMondayRate.setSelection(0)
            }
            if (data.mondayRate.equals(flucts)) {
                spinnerMondayRate.setSelection(i)
            }
            if (data.tuesdayRate == "1") {
                spinnerTuesdayRate.setSelection(0)
            }
            if (data.tuesdayRate.equals(flucts)) {
                spinnerTuesdayRate.setSelection(i)
            }
            if (data.wednesdayRate == "1") {
                spinnerWednesdayRate.setSelection(0)
            }
            if (data.wednesdayRate.equals(flucts)) {
                spinnerWednesdayRate.setSelection(i)
            }
            if (data.thursdayRate == "1") {
                spinnerThursdayRate.setSelection(0)
            }
            if (data.thursdayRate.equals(flucts)) {
                spinnerThursdayRate.setSelection(i)
            }
            if (data.fridayRate == "1") {
                spinnerFridayRate.setSelection(0)
            }
            if (data.fridayRate.equals(flucts)) {
                spinnerFridayRate.setSelection(i)
            }
            if (data.saturdayRate == "1") {
                spinnerSaturdayRate.setSelection(0)
            }
            if (data.saturdayRate.equals(flucts)) {
                spinnerSaturdayRate.setSelection(i)
            }
            if (data.sundayRate == "1") {
                spinnerSundayRate.setSelection(0)
            }
            if (data.sundayRate.equals(flucts)) {
                spinnerSundayRate.setSelection(i)
            }
            if (data.publicholidaysRate == "1") {
                spinnerPublicholidaysRate.setSelection(0)
            }
            if (data.publicholidaysRate.equals(flucts)) {
                spinnerPublicholidaysRate.setSelection(i)
            }
        }
    }

    private fun initView() {

        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "更新计价"

        tvSelectProduct = findViewById(R.id.tv_select_product) as TextView
        etStartPrice = findViewById(R.id.et_start_price) as EditText
        etStartKm = findViewById(R.id.et_start_km) as EditText
        etOutStartKmPrice = findViewById(R.id.et_outStartKmPrice) as EditText
        etLongDistanceKm = findViewById(R.id.et_longDistanceKm) as EditText
        etLongDistanceKmPrice = findViewById(R.id.et_longDistanceKmPrice) as EditText
        etSuperLongDistanceKm = findViewById(R.id.et_superLongDistanceKm) as EditText
        etSuperLongDistanceKmPrice = findViewById(R.id.et_superLongDistanceKmPrice) as EditText
        etMaxdistancekm = findViewById(R.id.et_maxdistancekm) as EditText
        etMaxdistancekmPrice = findViewById(R.id.et_maxdistancekmprice) as EditText
        etOtherPrice = findViewById(R.id.et_otherPrice) as EditText
        etNightFee = findViewById(R.id.et_nightFee) as EditText
        spinnerNightBegin = findViewById(R.id.spinner_nightBegin) as Spinner
        spinnerNightEnd = findViewById(R.id.spinner_nightEnd) as Spinner
        spinnerMondayRate = findViewById(R.id.spinner_mondayRate) as Spinner
        spinnerTuesdayRate = findViewById(R.id.spinner_tuesdayRate) as Spinner
        spinnerWednesdayRate = findViewById(R.id.spinner_wednesdayRate) as Spinner
        spinnerThursdayRate = findViewById(R.id.spinner_thursdayRate) as Spinner
        spinnerFridayRate = findViewById(R.id.spinner_fridayRate) as Spinner
        spinnerSaturdayRate = findViewById(R.id.spinner_saturdayRate) as Spinner
        spinnerSundayRate = findViewById(R.id.spinner_sundayRate) as Spinner
        spinnerPublicholidaysRate = findViewById(R.id.spinner_publicholidaysRate) as Spinner

        spinnerNightBegin.onItemSelectedListener = this
        spinnerNightEnd.onItemSelectedListener = this
        spinnerMondayRate.onItemSelectedListener = this
        spinnerTuesdayRate.onItemSelectedListener = this
        spinnerWednesdayRate.onItemSelectedListener = this
        spinnerThursdayRate.onItemSelectedListener = this
        spinnerFridayRate.onItemSelectedListener = this
        spinnerSaturdayRate.onItemSelectedListener = this
        spinnerSundayRate.onItemSelectedListener = this
        spinnerPublicholidaysRate.onItemSelectedListener = this

        tvNightBegin = findViewById(R.id.tv_nightBegin) as TextView
        tvNightEnd = findViewById(R.id.tv_nightEnd) as TextView
        btnSave = findViewById(R.id.btn_save_valuation) as Button

        if (data.frozen == 1) {
            btnSave.isEnabled = false
        }else{
            btnSave.isEnabled =true
        }

        tvNightBegin.setOnClickListener {
            timeBeginPicker.show()
        }
        timeBeginPicker.onSelectDate = object : TimePicker.OnSelectDate {
            override fun selectDate(date: String) {
                tvNightBegin.text = date
            }
        }

        tvNightEnd.setOnClickListener {
            timeEndPicker.show()
        }
        timeEndPicker.onSelectDate = object : TimePicker.OnSelectDate {
            override fun selectDate(date: String) {
                tvNightEnd.text = date
            }
        }


        btnSave.setOnClickListener {

            //1.先判断必填项不能为空
            if (TextUtil.isEmpty(etStartPrice.text.toString()) || etStartPrice.text.toString().toFloat() * 100 < 1) {
                ShowToast.showCenter(this, "起步价必须大于0")
                return@setOnClickListener
            }

            if (TextUtil.isEmpty(etStartKm.text.toString()) || etStartKm.text.toString().toFloat() * 100 < 1) {
                ShowToast.showCenter(this, "起步公里必须大于0")
                return@setOnClickListener
            }

            if (TextUtil.isEmpty(etOutStartKmPrice.text.toString()) || etOutStartKmPrice.text.toString().toFloat() * 100 < 1) {
                ShowToast.showCenter(this, "起步外单价必须大于0")
                return@setOnClickListener
            }

            //2.判断最长途公里数、超长途公里数、长途公里数、起步公里
            if (etMaxdistancekm.text.toString().isNotEmpty() && etMaxdistancekm.text.toString().substring(0, 1).toInt() > 0 && etSuperLongDistanceKm.text.toString().isNullOrEmpty()) {
                ShowToast.showCenter(this, "超长途公里数不能为空")
                return@setOnClickListener
            }

            if (etSuperLongDistanceKm.text.toString().isNotEmpty() && etSuperLongDistanceKm.text.toString().substring(0, 1).toInt() > 0 && etLongDistanceKm.text.toString().isNullOrEmpty()) {
                ShowToast.showCenter(this, "长途公里数不能为空")
                return@setOnClickListener
            }

            // 3.长途公里数 > 起步公里数

            if (etLongDistanceKm.text.toString().isNotEmpty() && etLongDistanceKm.text.toString().substring(0, 1).toInt() != 0 && etStartKm.text.toString().toDouble().toInt() >= etLongDistanceKm.text.toString().toDouble().toInt()) {
                ShowToast.showCenter(this, "长途公里数必须大于起步公里数")
                return@setOnClickListener
            }

            if (etMaxdistancekm.text.toString().isNotEmpty() && etMaxdistancekm.text.toString().substring(0, 1).toInt() != 0 && etSuperLongDistanceKm.text.toString().toDouble().toInt() >= etMaxdistancekm.text.toString().toDouble().toInt()) {
                ShowToast.showCenter(this, "最长途公里数必须大于超长途公里数")
                return@setOnClickListener
            }

            if (etSuperLongDistanceKm.text.toString().isNotEmpty() && etSuperLongDistanceKm.text.toString().substring(0, 1).toInt() != 0 && etLongDistanceKm.text.toString().toDouble().toInt() >= etSuperLongDistanceKm.text.toString().toDouble().toInt()) {
                ShowToast.showCenter(this, "超长途公里数必须大于长途公里数")
                return@setOnClickListener
            }


            //4.分开判断公里数和单价，二者必须同时存在

            if (etLongDistanceKm.text.toString().isNotEmpty() && etLongDistanceKm.text.toString().substring(0, 1).toInt() != 0 && etLongDistanceKmPrice.text.isNullOrEmpty()) {
                ShowToast.showCenter(this, "长途外单价不能为空")
                return@setOnClickListener
            }
            if (etLongDistanceKmPrice.text.toString().isNotEmpty() && etLongDistanceKmPrice.text.toString().substring(0, 1).toInt() != 0 && etLongDistanceKm.text.isNullOrEmpty()) {
                ShowToast.showCenter(this, "长途公里数不能为空")
                return@setOnClickListener
            }

            if (etSuperLongDistanceKm.text.toString().isNotEmpty() && etSuperLongDistanceKm.text.toString().substring(0, 1).toInt() != 0 && etSuperLongDistanceKmPrice.text.isNullOrEmpty()) {
                ShowToast.showCenter(this, "超长途单价不能为空")
                return@setOnClickListener
            }
            if (etSuperLongDistanceKmPrice.text.toString().isNotEmpty() && etSuperLongDistanceKmPrice.text.toString().substring(0, 1).toInt() != 0 && etSuperLongDistanceKm.text.toString().isNullOrEmpty()) {
                ShowToast.showCenter(this, "超长途公里数不能为空")
                return@setOnClickListener
            }

            if (etMaxdistancekm.text.toString().isNotEmpty() && etMaxdistancekm.text.toString().substring(0, 1).toInt() != 0 && etMaxdistancekmPrice.text.isNullOrEmpty()) {
                ShowToast.showCenter(this, "最长途单价不能为空")
                return@setOnClickListener
            }
            if (etMaxdistancekmPrice.text.toString().isNotEmpty() && etMaxdistancekmPrice.text.toString().substring(0, 1).toInt() != 0 && etMaxdistancekm.text.isNullOrEmpty()) {
                ShowToast.showCenter(this, "最长途公里数不能为空")
                return@setOnClickListener
            }


            //4.提交
            progress.show()
            presenter.updatePriceRule(userNo, userName, data.productNo, etStartPrice.text.toString(),
                    etStartKm.text.toString(), etOutStartKmPrice.text.toString(), etLongDistanceKm.text.toString(),
                    etLongDistanceKmPrice.text.toString(), etSuperLongDistanceKm.text.toString(),
                    etSuperLongDistanceKmPrice.text.toString(), etMaxdistancekm.text.toString(),
                    etMaxdistancekmPrice.text.toString(), etOtherPrice.text.toString(), etNightFee.text.toString(),
                    tvNightBegin.text.toString(), tvNightEnd.text.toString(), mondayRat, tuesdayRat, wednesdayRat, thursdayRat, fridayRat, saturdayRat, sundayRat, publicholidaysRat)

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == UpdateValuationEntity::class) {
            var data = any as UpdateValuationEntity
            if (data.rspCode.equals("00")) {
                ShowToast.showCenter(this, data.rspDesc)
                finish()
            } else {
                ShowToast.showCenter(this, data.rspDesc)
            }
        }
        progress.dismiss()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0!!.id) {
//            R.id.spinner_nightBegin -> {
//                val nightBegin = resources.getStringArray(R.array.nightBegin)
//                nightBeginTime = nightBegin[p2]
////                LogUtils.d("---nightBeginTime---", nightBeginTime)
//            }
//            R.id.spinner_nightEnd -> {
//                val nightEnd = resources.getStringArray(R.array.nightEnd)
//                nightEndTime = nightEnd[p2]
//            }
            R.id.spinner_mondayRate -> {
                val mondayRate = resources.getStringArray(R.array.fluctuation)
                if (p2 == 0) {
                    mondayRat = "1"
                } else {
                    mondayRat = (mondayRate[p2].substring(0, mondayRate[p2].toString().length - 1).toFloat() + 100).div(100).toString()
                }
//                LogUtils.d("---mondayRat---", mondayRat)
            }
            R.id.spinner_tuesdayRate -> {
                val tuesdayRate = resources.getStringArray(R.array.fluctuation)
                if (p2 == 0) {
                    tuesdayRat = "1"
                } else {
                    tuesdayRat = (tuesdayRate[p2].substring(0, tuesdayRate[p2].toString().length - 1).toFloat() + 100).div(100).toString()
                }
//                LogUtils.d("---tuesdayRat---", mondayRat)
            }
            R.id.spinner_wednesdayRate -> {
                val wednesdayRate = resources.getStringArray(R.array.fluctuation)
                if (p2 == 0) {
                    wednesdayRat = "1"
                } else {
                    wednesdayRat = (wednesdayRate[p2].substring(0, wednesdayRate[p2].toString().length - 1).toFloat() + 100).div(100).toString()
                }
            }
            R.id.spinner_thursdayRate -> {
                val thursdayRate = resources.getStringArray(R.array.fluctuation)
                if (p2 == 0) {
                    thursdayRat = "1"
                } else {
                    thursdayRat = (thursdayRate[p2].substring(0, thursdayRate[p2].toString().length - 1).toFloat() + 100).div(100).toString()
                }
            }
            R.id.spinner_fridayRate -> {
                val fridayRate = resources.getStringArray(R.array.fluctuation)
                if (p2 == 0) {
                    fridayRat = "1"
                } else {
                    fridayRat = (fridayRate[p2].substring(0, fridayRate[p2].toString().length - 1).toFloat() + 100).div(100).toString()
                }
            }
            R.id.spinner_saturdayRate -> {
                val saturdayRate = resources.getStringArray(R.array.fluctuation)
                if (p2 == 0) {
                    saturdayRat = "1"
                } else {
                    saturdayRat = (saturdayRate[p2].substring(0, saturdayRate[p2].toString().length - 1).toFloat() + 100).div(100).toString()
                }
            }
            R.id.spinner_sundayRate -> {
                val sundayRate = resources.getStringArray(R.array.fluctuation)
                if (p2 == 0) {
                    sundayRat = "1"
                } else {
                    sundayRat = (sundayRate[p2].substring(0, sundayRate[p2].toString().length - 1).toFloat() + 100).div(100).toString()
                }
            }
            R.id.spinner_publicholidaysRate -> {
                val publicholidaysRate = resources.getStringArray(R.array.fluctuation)
                if (p2 == 0) {
                    publicholidaysRat = "1"
                } else {
                    publicholidaysRat = (publicholidaysRate[p2].substring(0, publicholidaysRate[p2].toString().length - 1).toFloat() + 100).div(100).toString()
                }
            }
        }
    }

}