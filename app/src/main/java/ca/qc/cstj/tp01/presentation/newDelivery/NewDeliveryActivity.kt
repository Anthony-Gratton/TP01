package ca.qc.cstj.tp01.presentation.newDelivery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import ca.qc.cstj.tp01.R
import ca.qc.cstj.tp01.databinding.ActivityHomeBinding
import ca.qc.cstj.tp01.databinding.ActivityNewDeliveryBinding
import ca.qc.cstj.tp01.domain.models.Delivery
import ca.qc.cstj.tp01.domain.models.Trader
import ca.qc.cstj.tp01.presentation.deliveries.DeliveriesActivity
import ca.qc.cstj.tp01.presentation.main.HomeViewModel
import com.google.android.material.slider.Slider
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NewDeliveryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNewDeliveryBinding
    private val viewModel : NewDeliveryViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var trader:Trader = Trader()
        var delivery:Delivery = Delivery(0)
        viewModel.newDeliveryUiState.onEach {
            when(it){
                NewDeliveryUiState.Empty -> Unit
                is NewDeliveryUiState.Loading -> {
                     trader = it.trader
                    deactivateSliderRessources(trader.ewhyx, binding.sldEm)
                    binding.txtSliderEm.text = binding.sldEm.value.toString()

                    deactivateSliderRessources(trader.wusnyx, binding.sldWu)
                    binding.txtSliderWu.text = binding.sldWu.value.toString()


                    deactivateSliderRessources(trader.iaspyx, binding.sldI)
                    binding.txtSliderI.text = binding.sldI.value.toString()

                    deactivateSliderRessources(trader.smiathil, binding.sldSm)
                    binding.txtSliderSm.text = binding.sldSm.value.toString()

                    deactivateSliderRessources(trader.vathyx, binding.sldVe)
                    binding.txtSliderVe.text = binding.sldVe.value.toString()


                    delivery = Delivery()

                }
                is NewDeliveryUiState.Success -> {
                    binding.sldEm.value = 0F
                    binding.sldWu.value = 0F
                    binding.sldI.value = 0F
                    binding.sldSm.value = 0F
                    binding.sldVe.value = 0F
                    Toast.makeText(this, getString(R.string.delivery_saved), Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }.launchIn(lifecycleScope)



        binding.sldEm.addOnChangeListener { _, _, _ ->
            binding.txtSliderEm.text =String.format("%.2f", binding.sldEm.value)
            delivery.ewhyx = binding.sldEm.value

        }
        binding.sldWu.addOnChangeListener { _, _, _ ->
            binding.txtSliderWu.text = String.format("%.2f", binding.sldWu.value)
            delivery.wusnyx = binding.sldWu.value

        }
        binding.sldI.addOnChangeListener { _, _, _ ->
            binding.txtSliderI.text =String.format("%.2f", binding.sldI.value)
            delivery.iaspyx = binding.sldI.value

        }
        binding.sldSm.addOnChangeListener { _, _, _ ->
            binding.txtSliderSm.text = String.format("%.2f", binding.sldSm.value)
            delivery.smiathil = binding.sldSm.value

        }
        binding.sldVe.addOnChangeListener { _, _, _ ->
            binding.txtSliderVe.text = String.format("%.2f", binding.sldVe.value)
            delivery.vathyx = binding.sldVe.value

        }

        binding.fabSave.setOnClickListener{
            if (checkEmptyDelivery(delivery))
            {
                Toast.makeText(this, getString(R.string.delivery_content), Toast.LENGTH_LONG).show()
            } else  {
                viewModel.saveNewDelivery(delivery, trader)

            }
        }

    }



    private fun deactivateSliderRessources(traderElement : Float, slider: Slider ){
        if (traderElement <= 0){
            slider.isEnabled = false
        } else  {
            slider.valueTo = traderElement
        }
    }

    private fun checkEmptyDelivery(delivery: Delivery) : Boolean{
        var check = 0F
        check += delivery.ewhyx
        check += delivery.wusnyx
        check += delivery.iaspyx
        check += delivery.smiathil
        check += delivery.vathyx
        if (check > 0 ){
            return false
        }
        return true
    }


    companion object {
        fun newIntent(context : Context) : Intent {
            return Intent(context, NewDeliveryActivity::class.java)
        }
    }
}