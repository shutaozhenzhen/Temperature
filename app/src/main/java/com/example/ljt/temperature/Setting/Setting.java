package com.example.ljt.temperature.Setting;

public class Setting {
    private boolean bluetoothSwitch=false, buzzerSwitch=false, ledSwitch1=false, ledSwitch2=false;
    private Integer buzzerSlider=0, ledSlider1=0, ledSlider2=0;

    public boolean isBluetoothSwitch() {
        return bluetoothSwitch;
    }

    public void setBluetoothSwitch(boolean bluetoothSwitch) {
        this.bluetoothSwitch = bluetoothSwitch;
    }

    public boolean isBuzzerSwitch() {
        return buzzerSwitch;
    }

    public void setBuzzerSwitch(boolean buzzerSwitch) {
        this.buzzerSwitch = buzzerSwitch;
    }

    public boolean isLedSwitch1() {
        return ledSwitch1;
    }

    public void setLedSwitch1(boolean ledSwitch1) {
        this.ledSwitch1 = ledSwitch1;
    }

    public boolean isLedSwitch2() {
        return ledSwitch2;
    }

    public void setLedSwitch2(boolean ledSwitch2) {
        this.ledSwitch2 = ledSwitch2;
    }

    public Integer getBuzzerSlider() {
        return buzzerSlider;
    }

    public void setBuzzerSlider(Integer buzzerSlider) {
        this.buzzerSlider = buzzerSlider;
    }

    public Integer getLedSlider1() {
        return ledSlider1;
    }

    public void setLedSlider1(Integer ledSlider1) {
        this.ledSlider1 = ledSlider1;
    }

    public Integer getLedSlider2() {
        return ledSlider2;
    }

    public void setLedSlider2(Integer ledSlider2) {
        this.ledSlider2 = ledSlider2;
    }
/*

    public boolean isBluetoothSwitch() {
        return bluetoothSwitch;
    }

    public void setBluetoothSwitch(boolean bluetoothSwitch) {
        Switch aSwitch = (Switch) appCompatActivity.findViewById(R.id.bluetooth_switch);
        aSwitch.setChecked(bluetoothSwitch);
        this.bluetoothSwitch = bluetoothSwitch;
    }

    public boolean isBuzzerSwitch() {

        return buzzerSwitch;
    }

    public void setBuzzerSwitch(boolean buzzerSwitch) {
        Switch aSwitch = (Switch) appCompatActivity.findViewById(R.id.buzzer_switch);
        aSwitch.setChecked(buzzerSwitch);
        this.buzzerSwitch = buzzerSwitch;
    }

    public boolean isLedSwitch1() {
        return ledSwitch1;
    }

    public void setLedSwitch1(boolean ledSwitch1) {
        Switch aSwitch = (Switch) appCompatActivity.findViewById(R.id.LED_switch1);
        aSwitch.setChecked(ledSwitch1);
        this.ledSwitch1 = ledSwitch1;
    }

    public boolean isLedSwitch2() {
        return ledSwitch2;
    }

    public void setLedSwitch2(boolean ledSwitch2) {
        Switch aSwitch = (Switch) appCompatActivity.findViewById(R.id.LED_switch2);
        aSwitch.setChecked(ledSwitch2);
        this.ledSwitch2 = ledSwitch2;
    }

    public Integer getBuzzerSlider() {
        return buzzerSlider;
    }

    public void setBuzzerSlider(Integer buzzerSlider) {
        sliderDiscreteLayout slider = (sliderDiscreteLayout) appCompatActivity.findViewById(R.id.buzzer_slider);
        slider.setValue(buzzerSlider);
        this.buzzerSlider = buzzerSlider;
    }

    public Integer getLedSlider1() {
        return ledSlider1;
    }

    public void setLedSlider1(Integer ledSlider1) {

        sliderDiscreteLayout slider = (sliderDiscreteLayout) appCompatActivity.findViewById(R.id.LED_slider1);
        slider.setValue(ledSlider1);
        this.ledSlider1 = ledSlider1;
    }

    public Integer getLedSlider2() {
        return ledSlider2;
    }

    public void setLedSlider2(Integer ledSlider2) {

        sliderDiscreteLayout slider = (sliderDiscreteLayout) appCompatActivity.findViewById(R.id.LED_slider2);
        slider.setValue(ledSlider2);
        this.ledSlider2 = ledSlider2;
    }

    public Setting(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;

    }

    public AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public SettingFragment getSettingFragment() {
        return settingFragment;
    }

    public void replaceToSettingFragment(@IdRes int id) {
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, settingFragment);
        fragmentTransaction.commit();
    }

    public void save() {
        SharedPreferences sharedPreferences = appCompatActivity.getSharedPreferences("setting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        init();
        editor.putBoolean("bluetoothSwitch", bluetoothSwitch);
        editor.putBoolean("buzzerSwitch", buzzerSwitch);
        editor.putBoolean("ledSwitch1", ledSwitch1);
        editor.putBoolean("ledSwitch2", ledSwitch2);
        editor.putInt("buzzerSlider", buzzerSlider);
        editor.putInt("ledSlider1", ledSlider1);
        editor.putInt("ledSlider2", ledSlider2);
        editor.apply();
    }

    public void load() {
        SharedPreferences sharedPreferences = appCompatActivity.getSharedPreferences("setting", Context.MODE_PRIVATE);
        bluetoothSwitch = sharedPreferences.getBoolean("bluetoothSwitch", false);
        buzzerSwitch = sharedPreferences.getBoolean("buzzerSwitch", false);
        ledSwitch1 = sharedPreferences.getBoolean("ledSwitch1", false);
        ledSwitch2 = sharedPreferences.getBoolean("ledSwitch2", false);
        buzzerSlider = sharedPreferences.getInt("buzzerSlider", 0);
        ledSlider1 = sharedPreferences.getInt("ledSlider1", 0);
        ledSlider2= sharedPreferences.getInt("ledSlider2", 0);
    }

    public void init() {
        this.bluetoothSwitch = ((Switch) appCompatActivity.findViewById(R.id.bluetooth_switch)).isChecked();
        this.buzzerSwitch = ((Switch) appCompatActivity.findViewById(R.id.buzzer_switch)).isChecked();
        this.ledSwitch1 = ((Switch) appCompatActivity.findViewById(R.id.LED_switch1)).isChecked();
        this.ledSwitch2 = ((Switch) appCompatActivity.findViewById(R.id.LED_switch2)).isChecked();

        this.buzzerSlider = ((sliderDiscreteLayout) appCompatActivity.findViewById(R.id.buzzer_slider)).getValue();
        this.ledSlider1 = ((sliderDiscreteLayout) appCompatActivity.findViewById(R.id.LED_slider1)).getValue();
        this.ledSlider2 = ((sliderDiscreteLayout) appCompatActivity.findViewById(R.id.LED_slider2)).getValue();

    }

    public void setALL() {
        ((Switch) appCompatActivity.findViewById(R.id.bluetooth_switch)).setChecked(bluetoothSwitch);
        ((Switch) appCompatActivity.findViewById(R.id.buzzer_switch)).setChecked(buzzerSwitch);
        ((Switch) appCompatActivity.findViewById(R.id.LED_switch1)).setChecked(ledSwitch1);
        ((Switch) appCompatActivity.findViewById(R.id.LED_switch2)).setChecked(ledSwitch2);

        ((sliderDiscreteLayout) appCompatActivity.findViewById(R.id.buzzer_slider)).setValue(buzzerSlider);
        ((sliderDiscreteLayout) appCompatActivity.findViewById(R.id.LED_slider1)).setValue(ledSlider1);
        ((sliderDiscreteLayout) appCompatActivity.findViewById(R.id.LED_slider2)).setValue(ledSlider2);

    }

*/
}
