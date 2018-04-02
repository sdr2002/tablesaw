/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.tablesaw.util;

import com.google.common.base.Preconditions;
import tech.tablesaw.api.NumberColumn;
import tech.tablesaw.columns.Column;
import tech.tablesaw.table.TableSlice;
import tech.tablesaw.table.ViewGroup;

import java.util.List;

/**
 * Utility functions for creating 2D double arrays from columns and other arrays
 */
public class DoubleArrays {

    public static double[] toN(int n) {
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = i;
        }
        return result;
    }

    public static double[][] to2dArray(Column... columns) {
        Preconditions.checkArgument(columns.length >= 1);
        int obs = columns[0].size();
        double[][] allVals = new double[obs][columns.length];

        for (int r = 0; r < obs; r++) {
            for (int c = 0; c < columns.length; c++) {
                allVals[r][c] = columns[c].getDouble(r);
                allVals[r][c] = columns[c].getDouble(r);
            }
        }
        return allVals;
    }

    public static double[][] to2dArray(List<Column> columnList) {
        return to2dArray(columnList.toArray(new Column[columnList.size()]));
    }

    public static double[][] to2dArray(ViewGroup views, int columnNumber) {

        int viewCount = views.size();

        double[][] allVals = new double[viewCount][];
        for (int viewNumber = 0; viewNumber < viewCount; viewNumber++) {
            TableSlice view = views.get(viewNumber);
            allVals[viewNumber] = new double[view.rowCount()];
            NumberColumn numberColumn = view.numberColumn(columnNumber);
            for (int r = 0; r < view.rowCount(); r++) {
                allVals[viewNumber][r] = numberColumn.get(r);
            }
        }
        return allVals;
    }

    public static double[][] to2dArray(double[] x, double[] y) {
        double[][] allVals = new double[x.length][2];
        for (int i = 0; i < x.length; i++) {
            allVals[i][0] = x[i];
            allVals[i][1] = y[i];
        }
        return allVals;
    }

    public static double[][] to2dArray(Column x, Column y) {
        double[][] allVals = new double[x.size()][2];
        for (int i = 0; i < x.size(); i++) {
            allVals[i][0] = x.getDouble(i);
            allVals[i][1] = y.getDouble(i);
        }
        return allVals;
    }
}
