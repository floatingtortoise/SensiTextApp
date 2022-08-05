package com.example.sensitext

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sensitext.databinding.FragmentMainBinding
import com.example.sensitext.model.SentimentResponseModel
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private var outputText: TextView? = null
    private var inputText: EditText? = null
    private var button: Button? = null
    private val gson = Gson()


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val client = OkHttpClient()

    private fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .post(FormBody.Builder().build())
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val resultString = response.body?.string()
                val sentimentResponseModel: SentimentResponseModel = gson.fromJson(resultString, SentimentResponseModel::class.java)
                val score = sentimentResponseModel.score_tag
                val positivity =
                  if (score =="P+") { "strong positive"}
                  else if (score == "P") {"positive"}
                  else if (score =="NEU") { "neutral"}
                  else if (score =="N") {"negative"}
                  else if (score =="N+") {"strongly negative"}
                  else {"without polarity"}


                activity?.runOnUiThread {
                    outputText?.text = """
    This text is ${sentimentResponseModel.subjectivity.toLowerCase()} and ${sentimentResponseModel.irony.toLowerCase()}.
    This text contains ${sentimentResponseModel.agreement.toLowerCase()}.
    The tone is ${positivity}.
    The confidence score (out of 100) is: ${sentimentResponseModel.confidence}."""
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        outputText = view.findViewById(R.id.output)
        inputText = view.findViewById(R.id.input)
        button = view.findViewById(R.id.button)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button?.setOnClickListener {
            run("https://api.meaningcloud.com/sentiment-2.1?key=126bd3128ae0a988d2cc08000990f4d2&txt="+
                inputText?.text.toString())}
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}