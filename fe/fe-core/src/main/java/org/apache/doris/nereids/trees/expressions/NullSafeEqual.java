// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.nereids.trees.expressions;

import org.apache.doris.nereids.exceptions.UnboundException;
import org.apache.doris.nereids.trees.NodeType;

/**
 * Null safe equal expression: a <=> b.
 * Unlike normal equal to expression, null <=> null is true.
 */
public class NullSafeEqual<LEFT_CHILD_TYPE extends Expression, RIGHT_CHILD_TYPE extends Expression>
        extends ComparisonPredicate<LEFT_CHILD_TYPE, RIGHT_CHILD_TYPE> {
    /**
     * Constructor of Null Safe Equal ComparisonPredicate.
     *
     * @param left  left child of Null Safe Equal
     * @param right right child of Null Safe Equal
     */
    public NullSafeEqual(LEFT_CHILD_TYPE left, RIGHT_CHILD_TYPE right) {
        super(NodeType.NULL_SAFE_EQUAL, left, right);
    }

    @Override
    public boolean nullable() throws UnboundException {
        return false;
    }

    @Override
    public String toString() {
        return "(" + left() + " <=> " + right() + ")";
    }

    @Override
    public <R, C> R accept(ExpressionVisitor<R, C> visitor, C context) {
        return visitor.visitNullSafeEqual(this, context);
    }
}
