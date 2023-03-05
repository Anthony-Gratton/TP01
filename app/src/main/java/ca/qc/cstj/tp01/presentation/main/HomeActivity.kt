package ca.qc.cstj.tp01.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import ca.qc.cstj.tp01.databinding.ActivityHomeBinding
import ca.qc.cstj.tp01.domain.models.Trader
import ca.qc.cstj.tp01.presentation.deliveries.DeliveriesActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private val viewModel : HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var trader:Trader = Trader()

        viewModel.mainUiState.onEach {
            when(it){
                HomeUIState.Empty -> Unit

                is HomeUIState.Success -> {
                    trader = it.trader
                    binding.edtName.setText(it.trader.name)
                    binding.incElements.txtElementEx.text = it.trader.ewhyx.toString()
                    binding.incElements.txtElementWu.text = it.trader.wusnyx.toString()
                    binding.incElements.txtElementI.text = it.trader.iaspyx.toString()
                    binding.incElements.txtElementSm.text = it.trader.smiathil.toString()
                    binding.incElements.txtElementVe.text = it.trader.vathyx.toString()
                }
            }
        }.launchIn(lifecycleScope)

        binding.btnOpen.setOnClickListener{
            if (binding.edtName.text.toString().isNotBlank()) {
                viewModel.saveName(binding.edtName.text.toString())
                startActivity(DeliveriesActivity.newIntent(this))
            } else  {
                Toast.makeText(this, "The trader's name cannot be empty", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnReload.setOnClickListener{
            if (binding.edtName.text.toString().isNotBlank()) {
                viewModel.rechargeElements(trader)
            } else  {
                Toast.makeText(this, "The trader's name cannot be empty", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnUplaod.setOnClickListener{
            if (binding.edtName.text.toString().isNotBlank()) {
                viewModel.upload()
                Toast.makeText(this, "Your deliveries have been transferred to the Consortium", Toast.LENGTH_LONG).show()
            } else  {
                Toast.makeText(this, "The trader's name cannot be empty", Toast.LENGTH_LONG).show()
            }
        }
    }
}