package org.michaelbel.moviemade.presentation.common

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_backdrop.*
import org.michaelbel.moviemade.R
import org.michaelbel.moviemade.core.local.SharedPrefs.KEY_ACCOUNT_BACKDROP
import org.michaelbel.moviemade.presentation.App
import javax.inject.Inject

class BottomSheetDialog: BottomSheetDialogFragment() {

    companion object {
        private const val BACKDROP = "backdrop"

        internal fun newInstance(path: String): BottomSheetDialog {
            val args = Bundle()
            args.putString(BACKDROP, path)
            val fragment = BottomSheetDialog()
            fragment.arguments = args
            return fragment
        }
    }

    private var path: String? = null

    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App[requireActivity().application as App].createFragmentComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_backdrop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        path = arguments?.getString(BACKDROP) ?: ""

        setBtn.setOnClickListener {
            preferences.edit().putString(KEY_ACCOUNT_BACKDROP, path).apply()
            Toast.makeText(activity, R.string.msg_done, Toast.LENGTH_SHORT).show()
            dismiss()
        }

        cancelBtn.setOnClickListener { dismiss() }
    }
}