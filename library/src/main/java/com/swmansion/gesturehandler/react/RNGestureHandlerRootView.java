/*
 * Copyright (c) 2019. DNA Software. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.views.view.ReactViewGroup;

public class RNGestureHandlerRootView extends ReactViewGroup {

  private @Nullable
  RNGestureHandlerRootHelper mRootHelper;

  public RNGestureHandlerRootView(Context context) {
    super(context);
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    if (mRootHelper == null) {
      mRootHelper = new RNGestureHandlerRootHelper((ReactContext) getContext(), this);
    }
  }

  public void tearDown() {
    if (mRootHelper != null) {
      mRootHelper.tearDown();
    }
  }

  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    if (Assertions.assertNotNull(mRootHelper).dispatchTouchEvent(ev)) {
      return true;
    }
    return super.dispatchTouchEvent(ev);
  }

  @Override
  public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    Assertions.assertNotNull(mRootHelper).requestDisallowInterceptTouchEvent(disallowIntercept);
    super.requestDisallowInterceptTouchEvent(disallowIntercept);
  }
}
