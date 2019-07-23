package com.it.rxapp_manager_android.module.act

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.it.rxapp_manager_android.R
import com.it.rxapp_manager_android.modle.CreateValuationEntity
import com.it.rxapp_manager_android.modle.ListBasicAuthCityEntity
import com.it.rxapp_manager_android.module.base.ComponentHolder
import com.it.rxapp_manager_android.module.base.MyPresenter
import com.it.rxapp_manager_android.module.base.ProductType
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
 * Created by deqiangchen on 2018/10/22 14:20
 */
class CreateValuationActivity : BaseActivity(), AdapterView.OnItemSelectedListener {


    private lateinit var rlSelectProduct: RelativeLayout
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
    private lateinit var btnSave: Button
    private lateinit var tvNightBegin: TextView
    private lateinit var tvNightEnd: TextView
    private var product: ListBasicAuthCityEntity.AuthCitysBean? = null

    lateinit var userNo: String
    lateinit var userName: String
    lateinit var nightBeginTime: String
    lateinit var nightEndTime: String
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
    private lateinit var timeBeginPicker: TimePicker
    private lateinit var timeEndPicker: TimePicker

    companion object {
        @JvmStatic
        fun startCreateValuationActivity(context: Activity, userNo: String) {
            context.startActivity(Intent(context, CreateValuationActivity::class.java).putExtra(Constants.USER_NO, userNo))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_valuation)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        ComponentHolder.appComponent!!.inject(this)
        presenter.register(this)
        progress = MyProgress(this)
        timeBeginPicker = TimePicker(this)
        timeEndPicker = TimePicker(this)
        userNo = intent.getStringExtra(Constants.USER_NO)
        userName = UserInfoPreferences.getInstance().mobile
        initView()
    }

    private fun initView() {
        setToolbar(toolbar = findViewById(R.id.toolbar) as Toolbar)
        (findViewById(R.id.tv_toolbar_title) as TextView).text = "新增计价"

        rlSelectProduct = findViewById(R.id.rl_select_product) as RelativeLayout
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

        tvNightBegin = findViewById(R.id.tv_nightBegin) as TextView
        tvNightEnd = findViewById(R.id.tv_nightEnd) as TextView
        btnSave = findViewById(R.id.btn_save_valuation) as Button


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

        rlSelectProduct.setOnClickListener {
            ListBasicAuthCityActivity.startListBasicAuthCityActivity(this, userNo)
        }
        btnSave.setOnClickListener {
            //1.判断必填项不能为空
            if (TextUtil.isEmpty(tvSelectProduct.text.toString())) {
                ShowToast.showCenter(this, "产品不能为空")
                return@setOnClickListener
            }

            if (TextUtil.isEmpty(etStartPrice.text.toString())) {
                ShowToast.showCenter(this, "起步价不能为空")
                return@setOnClickListener
            }

            if (TextUtil.isEmpty(etStartKm.text.toString())) {
                ShowToast.showCenter(this, "起步公里不能为空")
                return@setOnClickListener
            }

            if (TextUtil.isEmpty(etOutStartKmPrice.text.toString())) {
                ShowToast.showCenter(this, "起步外单价不能为空")
                return@setOnClickListener
            }

            //2.判断最长途公里数、超长途公里数、长途公里数、起步公里

            if (etMaxdistancekm.text.toString().isNotEmpty() && etSuperLongDistanceKm.text.toString().isNullOrEmpty()) {
                ShowToast.showCenter(this, "超长途公里数不能为空")
                return@setOnClickListener
            }

            if (etSuperLongDistanceKm.text.toString().isNotEmpty() && etLongDistanceKm.text.toString().isNullOrEmpty()) {
                ShowToast.showCenter(this, "长途公里数不能为空")
                return@setOnClickListener
            }

            // 长途公里数 > 起步公里数
            if (etMaxdistancekm.text.toString().isNotEmpty() && etMaxdistancekm.text.toString().substring(0, 1).toInt() != 0 && etSuperLongDistanceKm.text.toString().toDouble().toInt() >= etMaxdistancekm.text.toString().toDouble().toInt()) {
                ShowToast.showCenter(this, "最长途公里数必须大于超长途公里数")
                return@setOnClickListener
            }

            if (etSuperLongDistanceKm.text.toString().isNotEmpty() && etSuperLongDistanceKm.text.toString().substring(0, 1).toInt() != 0 && etLongDistanceKm.text.toString().toDouble().toInt() >= etSuperLongDistanceKm.text.toString().toDouble().toInt()) {
                ShowToast.showCenter(this, "超长途公里数必须大于长途公里数")
                return@setOnClickListener
            }

            if (etLongDistanceKm.text.toString().isNotEmpty() && etLongDistanceKm.text.toString().substring(0, 1).toInt() != 0 && etStartKm.text.toString().toDouble().toInt() >= etLongDistanceKm.text.toString().toDouble().toInt()) {
                ShowToast.showCenter(this, "长途公里数必须大于起步公里数")
                return@setOnClickListener
            }

            //3.分开判断公里数和单价，二者必须同时存在
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
            presenter.addPriceRule(userNo, userName, etStartPrice.text.toString(),
                    etStartKm.text.toString(), product!!.orgName, etOutStartKmPrice.text.toString(),
                    product!!.productType, product!!.usetype, product!!.startCity, product!!.startcityname, product!!.endCity,
                    product!!.endcityname, product!!.authCityId,
                    tvSelectProduct.text.toString(),
                    product!!.org_incity_cartype,
                    product!!.cartype, product!!.cartypename, product!!.isonline,
                    product!!.linetype, product!!.linetypename, product!!.isPush,
                    product!!.isInquire, etLongDistanceKm.text.toString(),
                    etLongDistanceKmPrice.text.toString(), etSuperLongDistanceKm.text.toString(),
                    etSuperLongDistanceKmPrice.text.toString(), etMaxdistancekm.text.toString(),
                    etMaxdistancekmPrice.text.toString(), etOtherPrice.text.toString(),
                    etNightFee.text.toString(), tvNightBegin.text.toString(), tvNightEnd.text.toString(),
                    mondayRat, tuesdayRat, wednesdayRat, thursdayRat, fridayRat, saturdayRat, sundayRat, publicholidaysRat)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.REQUEST_SELECT_PRODUCT_ACTIVITY) {
            product = data!!.getSerializableExtra(Constants.ACTIVITY_BACK_DATA) as ListBasicAuthCityEntity.AuthCitysBean
            var useType = ProductType.getKey(product!!.productType.toInt())
            if (product!!.usetype.equals("1")) {
                useType += "机"
            } else if (product!!.usetype.equals("2")) {
                useType += "火车"
            }
//            tvSelectProduct.text = "[" + product!!.linetypename + "]" + product!!.startcityname + "--" + product!!.endcityname + "--" + useType + "--" + product!!.cartypename
            tvSelectProduct.text = "出发城市[" + product!!.startcityname + "]目的地城市[" + product!!.endcityname + "]服务类型[" + useType + "]车型[" + product!!.cartypename + "]"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unregister(this)
    }

    @Subscribe
    fun loadData(any: Any) {
        if (any::class == CreateValuationEntity::class) {
            var data = any as CreateValuationEntity
            if (data.rspCode.equals("00")) {
                ShowToast.showCenter(this, data.rspDesc)
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