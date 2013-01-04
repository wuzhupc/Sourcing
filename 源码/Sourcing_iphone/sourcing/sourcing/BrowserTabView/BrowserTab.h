//
//  BrowserTab.h
//  BrowserTabViewDemo
//
//  Created by xiao haibo on 9/9/12.
//  Copyright (c) 2012 xiao haibo. All rights reserved.

//  github:https://github.com/xxhp/BrowserTabViewDemo
//  Email:xiao_hb@qq.com

//  Permission is hereby granted, free of charge, to any person
//  obtaining a copy of this software and associated documentation
//  files (the "Software"), to deal in the Software without
//  restriction, including without limitation the rights to use,
//  copy, modify, merge, publish, distribute, sublicense, and/or sell
//  copies of the Software, and to permit persons to whom the
//  Software is furnished to do so, subject to the following
//  conditions:
//
//  The above copyright notice and this permission notice shall be
//  included in all copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
//  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
//  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
//  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
//  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
//  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
//  OTHER DEALINGS IN THE SOFTWARE.
//

#import <UIKit/UIKit.h>
#import "BrowserTabView.h"
@class BrowserTabView;
@interface BrowserTab : UIView{
    //font of tab title
    UIFont *titleFont;
    
    //color for tab title when tab been normal state
    UIColor *normalTitleColor;
    
    //  image  for tab been selected
    UIImage *tabSelectedImage;
    
    //  image  for tab been normal state
    UIImage *tabNormalImage;
     
    NSString *reuseIdentifier;
    UIImageView *imageView;
    
    NSInteger index;
    
    BOOL previousSelected ;
    BOOL selected;
    
    UIPanGestureRecognizer *panGuesture;
    BrowserTabView *delegate;
}
@property UIFont *titleFont;
@property NSString *title;
@property BOOL selected;
@property UIImage *tabSelectedImage;
@property UIImage *tabNormalImage;
@property UIColor *normalTitleColor;
@property UIColor *selectedTitleColor;
@property UIImageView *imageView;;
@property UILabel *textLabel;
@property NSString *reuseIdentifier;
@property NSInteger index;
@property BrowserTabView * delegate;

-(id)initWithReuseIdentifier:(NSString *)aReuseIdentifier andDelegate:(id)aDelegate;
-(void)prepareForReuse;
@end
