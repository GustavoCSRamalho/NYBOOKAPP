package gustavo.com.nyapp.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import gustavo.com.nyapp.R
import kotlinx.android.synthetic.main.include_toolbar.*

open class BaseActivity: AppCompatActivity(){

    protected fun setupToolbar(toolbar: Toolbar, titleIdRes: Int,showBackButton: Boolean = false) {
        toolbar.title = getString(titleIdRes)
        setSupportActionBar(toolbarMain)

        if (showBackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
        }
    }
}