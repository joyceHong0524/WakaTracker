/**
 * @author Joyce Hong
 * @email soja0524@gmail.com
 * @created 2019-11-12
 * @desc
 */

package com.junga.wakatracker.retrofit

class RawResponseData {
    var data: Array<Data>? = null

    var timezone: String? = null

    var start: String? = null

    var end: String? = null

    var branches: Array<String>? = null

    override fun toString(): String {
        return "ClassPojo [data = $data, timezone = $timezone, start = $start, end = $end, branches = $branches]"
    }
}
