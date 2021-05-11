package uz.task.optimaltask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var touchHelper: ItemTouchHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = readFromAsset()

        val adapter = CustomAdapter(model, this)

        recyclerView.layoutManager = GridLayoutManager(this, 4)
        recyclerView.adapter = adapter

        val callback: ItemTouchHelper.Callback =
            ItemMoveCallback(adapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper!!.attachToRecyclerView(recyclerView)
    }

    private fun readFromAsset(): List<Model> {

        val modeList = mutableListOf<Model>()

        val bufferReader = application.assets.open("android_version.json").bufferedReader()
        val json_string = bufferReader.use {
            it.readText()
        }

        val jsonArray = JSONArray(json_string)
        for (i in 0 until jsonArray.length()) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(i)
            val model = Model(
                jsonObject.getString("name"),
                jsonObject.getString("version")
            )
            modeList.add(model)
        }
        return modeList
    }

}
