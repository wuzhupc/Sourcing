//
//  RelativityLaws.h
//  LayoutOfRelativity
//
//  Created by Josh Holtz on 4/27/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "OHAttributedLabel.h"


@interface RelativityLaws : NSObject

+ (void)labelFitHeight:(UILabel*)label;

+ (void)alignView:(UIView*)view below:(UIView*)otherView withMargin:(NSInteger)margin;

+ (void)alignViewTop:(UIView*)view below:(UIView*)otherView withMargin:(NSInteger)margin;

+ (void)alignParentBottom:(UIView*)view toView:(UIView*)otherView withPadding:(NSInteger)padding;

+ (void)attributedLabelFitHeight:(OHAttributedLabel *)label;

+ (OHAttributedLabel *)attributedLabelWithUILabel:(UILabel *)label;

@end
