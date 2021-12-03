package uz.task.optimaltask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import org.json.JSONArray
import org.json.JSONObject
import uz.task.optimaltask.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(),
    ItemMoveCallback.ItemTouchHelperContract {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var touchHelper: ItemTouchHelper? = null
    private val list = mutableListOf<Model>()
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list.clear()
        list.addAll(readFromAsset())
        adapter = CustomAdapter(list)
        binding.recyclerView.layoutManager = GridLayoutManager(this, 4)
        binding.recyclerView.adapter = adapter
        val callback: ItemTouchHelper.Callback = ItemMoveCallback(this)
        touchHelper = ItemTouchHelper(callback)
        touchHelper?.attachToRecyclerView(binding.recyclerView)
    }

    private fun readFromAsset(): List<Model> {
        val modeList = mutableListOf<Model>()
        val bufferReader = application.assets.open("android_version.json").bufferedReader()
        val jsonString = bufferReader.use { it.readText() }
        val jsonArray = JSONArray(jsonString)
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

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(list, fromPosition, toPosition)
        adapter.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onChildLeft(pos: Int) {
        Log.d("TAGTAG", "onChildLeft $pos")
//        TODO("Not yet implemented")
    }

    override fun onChildRight(pos: Int) {
        Log.d("TAGTAG", "onChildRight $pos")
//        TODO("Not yet implemented")
    }

}
