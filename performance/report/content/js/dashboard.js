/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 99.94058229352348, "KoPercent": 0.059417706476530004};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.9613784907902555, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.97, 500, 1500, "Get Bug Report Page"], "isController": false}, {"data": [1.0, 500, 1500, "Get Login Page-2"], "isController": false}, {"data": [1.0, 500, 1500, "Get Login Page-1"], "isController": false}, {"data": [1.0, 500, 1500, "Get Login Page-0"], "isController": false}, {"data": [1.0, 500, 1500, "Post Username-0"], "isController": false}, {"data": [1.0, 500, 1500, "Post Username-2"], "isController": false}, {"data": [1.0, 500, 1500, "Post Username-1"], "isController": false}, {"data": [1.0, 500, 1500, "Post Bug Report"], "isController": false}, {"data": [0.8854961832061069, 500, 1500, "Get Login Page"], "isController": false}, {"data": [0.875, 500, 1500, "Post Username"], "isController": false}, {"data": [1.0, 500, 1500, "Post Bug Report-0"], "isController": false}, {"data": [1.0, 500, 1500, "Post Login-3"], "isController": false}, {"data": [1.0, 500, 1500, "Post Bug Report-1"], "isController": false}, {"data": [1.0, 500, 1500, "Get Bug Report Page-1"], "isController": false}, {"data": [1.0, 500, 1500, "Post Login-2"], "isController": false}, {"data": [1.0, 500, 1500, "Get Bug Report Page-0"], "isController": false}, {"data": [0.7155172413793104, 500, 1500, "Post Login"], "isController": false}, {"data": [1.0, 500, 1500, "Post Login-1"], "isController": false}, {"data": [1.0, 500, 1500, "Get Bug Report Page-3"], "isController": false}, {"data": [1.0, 500, 1500, "Post Login-0"], "isController": false}, {"data": [1.0, 500, 1500, "Get Bug Report Page-2"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 1683, 1, 0.059417706476530004, 222.90968508615578, 62, 1006, 161.0, 460.2000000000003, 565.3999999999999, 734.2000000000016, 164.61267605633802, 3668.1496585851914, 75.99450053183685], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Get Bug Report Page", 50, 0, 0.0, 204.90000000000003, 95, 886, 140.0, 479.7, 554.0499999999995, 886.0, 5.475251861585633, 95.20607479194044, 2.3471934201160756], "isController": false}, {"data": ["Get Login Page-2", 111, 0, 0.0, 202.41441441441452, 124, 362, 190.0, 265.0, 284.5999999999998, 357.3199999999998, 11.649874055415617, 592.3805781315596, 3.166551053473971], "isController": false}, {"data": ["Get Login Page-1", 111, 0, 0.0, 125.29729729729729, 74, 229, 117.0, 175.0, 204.39999999999998, 227.91999999999996, 11.71009600168794, 7.295938719801668, 3.102870490030594], "isController": false}, {"data": ["Get Login Page-0", 111, 0, 0.0, 134.6036036036036, 75, 287, 126.0, 186.6, 208.79999999999998, 284.1199999999999, 11.67192429022082, 59.453864353312305, 3.1497477983701367], "isController": false}, {"data": ["Post Username-0", 104, 0, 0.0, 133.05769230769232, 81, 285, 125.5, 177.0, 201.0, 283.75000000000006, 11.41602634467618, 59.971978766465426, 4.523703347969265], "isController": false}, {"data": ["Post Username-2", 104, 0, 0.0, 197.44230769230768, 125, 323, 191.0, 243.5, 268.0, 320.85000000000014, 11.498065229408512, 584.5415932490326, 3.121221151188502], "isController": false}, {"data": ["Post Username-1", 104, 0, 0.0, 131.92307692307688, 78, 280, 119.0, 195.0, 217.75, 278.75000000000006, 11.476495254910615, 7.150394504524388, 3.036913243489296], "isController": false}, {"data": ["Post Bug Report", 50, 0, 0.0, 318.1600000000001, 224, 495, 294.5, 416.2, 474.44999999999993, 495.0, 5.434191935659168, 120.01497799152266, 5.243889081349853], "isController": false}, {"data": ["Get Login Page", 131, 0, 0.0, 409.3358778625955, 77, 804, 413.0, 606.4, 662.5999999999998, 787.0400000000004, 12.855740922473013, 626.2800731415604, 9.026350895485772], "isController": false}, {"data": ["Post Username", 124, 0, 0.0, 405.45161290322585, 70, 711, 411.5, 602.5, 634.75, 702.75, 12.263871031549797, 593.7011284121254, 10.170698991197705], "isController": false}, {"data": ["Post Bug Report-0", 50, 0, 0.0, 158.10000000000002, 115, 266, 148.0, 205.8, 241.39999999999995, 266.0, 5.498735290883096, 3.415981902287474, 3.7723257244583746], "isController": false}, {"data": ["Post Login-3", 87, 0, 0.0, 205.183908045977, 149, 395, 190.0, 262.2, 318.39999999999986, 395.0, 8.945095619987661, 439.84501513340524, 2.3616827639831377], "isController": false}, {"data": ["Post Bug Report-1", 50, 0, 0.0, 159.80000000000004, 108, 262, 156.0, 222.49999999999997, 255.79999999999998, 262.0, 5.559879906594018, 119.33684966779717, 1.550902438007339], "isController": false}, {"data": ["Get Bug Report Page-1", 8, 0, 0.0, 140.5, 82, 348, 117.0, 348.0, 348.0, 348.0, 1.4443040259974724, 0.9506454233616177, 0.449934554973822], "isController": false}, {"data": ["Post Login-2", 116, 0, 0.0, 129.06896551724137, 68, 256, 122.0, 186.19999999999996, 220.29999999999998, 253.45, 12.015744769007664, 42.025973689662315, 3.181566578620261], "isController": false}, {"data": ["Get Bug Report Page-0", 8, 0, 0.0, 117.25, 90, 183, 110.0, 183.0, 183.0, 183.0, 1.4508523757707652, 0.9521218715995647, 0.4080522306855277], "isController": false}, {"data": ["Post Login", 116, 1, 0.8620689655172413, 541.3275862068966, 230, 1006, 516.5, 800.4999999999999, 918.9, 1004.64, 11.650095410264136, 487.2315213229386, 13.459143849804157], "isController": false}, {"data": ["Post Login-1", 116, 0, 0.0, 128.5, 62, 281, 116.5, 183.6, 216.09999999999988, 276.5799999999999, 12.048192771084338, 7.444818335843373, 3.5313668466971335], "isController": false}, {"data": ["Get Bug Report Page-3", 8, 0, 0.0, 150.75, 100, 224, 140.0, 224.0, 224.0, 224.0, 1.4681592952835383, 26.462711047898697, 0.44876353459350343], "isController": false}, {"data": ["Post Login-0", 116, 0, 0.0, 128.98275862068968, 68, 312, 120.0, 172.89999999999998, 196.45, 302.81999999999994, 12.114882506527415, 10.029679340731072, 4.8384464751958225], "isController": false}, {"data": ["Get Bug Report Page-2", 8, 0, 0.0, 131.0, 96, 159, 130.0, 159.0, 159.0, 159.0, 1.453752498637107, 1.0888946938033799, 0.45571733599854625], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["The operation lasted too long: It took 1,006 milliseconds, but should not have lasted longer than 1,000 milliseconds.", 1, 100.0, 0.059417706476530004], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 1683, 1, "The operation lasted too long: It took 1,006 milliseconds, but should not have lasted longer than 1,000 milliseconds.", 1, "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["Post Login", 116, 1, "The operation lasted too long: It took 1,006 milliseconds, but should not have lasted longer than 1,000 milliseconds.", 1, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
