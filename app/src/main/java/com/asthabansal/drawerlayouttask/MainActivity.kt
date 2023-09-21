package com.asthabansal.drawerlayouttask

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.asthabansal.drawerlayouttask.databinding.ActivityMainBinding
import com.asthabansal.drawerlayouttask.databinding.BottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    var count = 1
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var adapter:RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Singelton.sharedPreferences?.init(this)
        gridLayoutManager = GridLayoutManager(this,updateCount())
        binding.recycler.layoutManager = gridLayoutManager
        adapter = RecyclerAdapter()
        binding.recycler.adapter = adapter

        binding.tvname.setText(Singelton.sharedPreferences?.getName(AppConstants.name))

        var time = SimpleDateFormat("hh:mm").format(Calendar.getInstance().time)
        binding.tvtextClock.setText(time.toString())
        //binding.tvTime.setText(time.toString())

        if(time >= "00:00:00" && time< "12:00:00"){
            binding.tvwish.setText("Good Morning")
        }
        else if(time >="12:00:00" && time <"16:00:00" ){
            binding.tvwish.setText("Good Afternoon")
        }
        else if(time >="16:00:00" && time <"20:00:00" ){
            binding.tvwish.setText("Good Evening")
        }
        else
        {
            binding.tvwish.setText("Good Night")
        }

        binding.btnUpdateName.setOnClickListener {
            var bottomSheetDialog = BottomSheetDialog(this)
            var dialogBinding = BottomSheetDialogBinding.inflate(layoutInflater)
            dialogBinding.activity = this
            bottomSheetDialog.setContentView(dialogBinding.root)

            bottomSheetDialog.show()
            dialogBinding.btnSave.setOnClickListener {

                if (dialogBinding.etEnterName.text.toString().isNullOrEmpty())
                {
                    dialogBinding.etEnterName.error = "enter a name"
                } else {
                    count = count++
                    adapter.updateCount(count)
                    Singelton.sharedPreferences.saveName(AppConstants.name, dialogBinding.etEnterName.text.toString())
                    binding.tvname.setText(Singelton.sharedPreferences?.getName(AppConstants.name))
                    bottomSheetDialog.dismiss()


                }
            }

        /*if(updateCount()!= 0) {
                dialogBinding.lastUpdatedDate.setText(updatedDate())
                dialogBinding.lastUpdatedTime.setText(updatedTime().toString())
            }
            else{
            dialogBinding.lastUpdatedDate.setText("")
            dialogBinding.lastUpdatedTime.setText("")}*/


             /*when (count) {
                count++ -> {
                    dialogBinding.lastUpdatedDate.setText(updatedDate())
                    dialogBinding.lastUpdatedTime.setText(updatedTime())
                }*/

        }
            /*count = count++
            updateCount()
            if (updateCount() ==null){
                dialogBinding.lastUpdatedDate.setText("")
            }
            else{
                dialogBinding.lastUpdatedDate.setText(updatedDate().toString())
            }*/


        binding.btnDeleteName.setOnClickListener {
            binding.tvname.setText("Name")
        }
    }

    /*fun updatedDate():String{
        var date = SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance())
        var Date = date.toString()
        return Date
    }
    fun updatedTime() {
        var time = SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance()).toString()
        //return time
    }*/
    fun updateCount():Int{
        count = count++
        adapter.updateCount(count)

        return count
    }
}