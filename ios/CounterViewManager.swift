//
//  CounterViewManager.swift
//  ReactNativeBridgeVLC
//
//  Created by Jardel Kuhn on 11/01/22.
//

import Foundation

@objc(CounterViewManager)
class CounterViewManager: RCTViewManager {
  override func view() -> UIView! {
    let label = UILabel()
    label.text = "Text label written in swift code"
    label.textAlignment = .center
    return label
  }
}
