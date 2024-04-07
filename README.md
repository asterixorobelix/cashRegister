# cashRegister

![Screenshot](https://drive.google.com/uc?export=view&id=1tsBTnwXfjU7wbp2GM__VC_diA3A3xKB8)

## Architecture
* [Koin](https://github.com/InsertKoinIO/koin)
* ViewModel
* Fragment
* [Jetpack Compose](https://developer.android.com/develop/ui/compose)

## Features
* Suitable empty state provided
* Currency amount entry by the user
* Most recent entry shown at the top
* Scrollable list of amounts
* Total amount displayed
* Amounts less than R1 Million allowed for each entry (R999 999,99 max)
* Non-zero and non-negative amounts not allowed

## Possible Enhancements
* CI/CD pipeline
* Save data to local Room database
* More tests (beside the existing `CurrencyUtilsTests`)
* Negative numbers are automatically converted to positives and added, display suitable message to user when this is done
* Display a suitable error message when invalid amounts are entered
* Centralise paddings, styles in some places

## Notes
In the interest of time, a suitable Android Native keyboard is used instead of the custom keyboard required by the design. This deviation from the design should also be more intuitive to the user, while also being less bug prone.
