import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.todoreward.getRandomString
import androidx.fragment.app.activityViewModels
import com.example.todoreward.R
import com.example.todoreward.RewardItem
import com.example.todoreward.RewardItemModel

class DialogAddRwd : DialogFragment() {

    companion object {
        const val TAG = "Dialog Add Reward"
    }

    private val viewModel: RewardItemModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.dialog_add_rwd, container, false)

        var addBut = view.findViewById<Button>(R.id.butAddReward)
        addBut.setOnClickListener()
        {
            val rewardItemData = RewardItem.createRewardItem()

            var taskNameText =
                view.findViewById<TextView>(R.id.editTextRwdName)

            rewardItemData.UID = getRandomString(Int.SIZE_BITS - 1)

            // Set a new item
            viewModel.setItem(rewardItemData)
            this.dismiss()

        }

        return view
    }
}