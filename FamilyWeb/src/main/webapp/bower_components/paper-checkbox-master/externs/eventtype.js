/**
 * @license
 * Copyright (c) 2014 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt. The complete set of authors may be
 * found at http://polymer.github.io/AUTHORS.txt. The complete set of
 * contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt. Code
 * distributed by Google as part of the polymer project is also subject to an
 * additional IP rights grant found at http://polymer.github.io/PATENTS.txt.
 */

goog.provide('PaperCheckboxEventType');


/**
 * Constants for event names fired by paper-checkbox elements.
 * @enum {string}
 */
PaperCheckboxEventType = {
  // Fired when the checked state changes due to user interaction.
  CHANGE: 'change',
  // Fired when the checked state changes.
  CORE_CHANGE: 'core-change'
};