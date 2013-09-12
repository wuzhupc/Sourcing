//
//  RelativityLaws.m
//  LayoutOfRelativity
//
//  Created by Josh Holtz on 4/27/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "RelativityLaws.h"
#import "OHASBasicHTMLParser.h"

@implementation RelativityLaws

+ (void)labelFitHeight:(UILabel*)label {
    CGSize maximumLabelSize = CGSizeMake(label.frame.size.width,CGFLOAT_MAX);
    CGSize expectedLabelSize = [label.text sizeWithFont:label.font constrainedToSize:maximumLabelSize lineBreakMode:label.lineBreakMode];
    
    CGRect newFrame = CGRectMake(label.frame.origin.x, label.frame.origin.y, maximumLabelSize.width, expectedLabelSize.height);
    label.frame = newFrame;
}

+ (void)attributedLabelFitHeight:(OHAttributedLabel *)label
{
    if (label == nil) {
        return;
    }
    NSLog(@"label before:%f",label.frame.size.width);
    NSAttributedString* attrStr = label.attributedText;
    CGSize sz = [attrStr sizeConstrainedToSize:CGSizeMake(label.frame.size.width, CGFLOAT_MAX)];
    CGRect newFrame = CGRectMake(label.frame.origin.x, label.frame.origin.y, sz.width, sz.height);
    label.frame = newFrame;
    
    NSLog(@"label after:%f",label.frame.size.width);
}

+ (OHAttributedLabel *)attributedLabelWithUILabel:(UILabel *)label
{
    if (label == nil) {
        return  nil;
    }
    OHAttributedLabel *result = [[OHAttributedLabel alloc] initWithFrame:label.frame];
    result.autoresizingMask = label.autoresizingMask;
    result.automaticallyAddLinksForType = NSTextCheckingAllTypes;
    result.highlightedTextColor = label.highlightedTextColor;
    result.tag = label.tag;
    result.attributedText = [OHASBasicHTMLParser attributedStringByProcessingMarkupInString:label.text];
    return result;
}

+ (void)alignView:(UIView*)view below:(UIView*)otherView withMargin:(NSInteger)margin {
    
    CGRect newViewFrame = CGRectMake(view.frame.origin.x, otherView.frame.origin.y + otherView.frame.size.height + margin, view.frame.size.width, view.frame.size.height);
    
    [view setFrame:newViewFrame];
}

+ (void)alignViewTop:(UIView*)view below:(UIView*)otherView withMargin:(NSInteger)margin {
    
    NSInteger height = (view.frame.origin.y + view.frame.size.height)- otherView.frame.size.height - margin;
    
    CGRect newViewFrame = CGRectMake(view.frame.origin.x, otherView.frame.origin.y + otherView.frame.size.height + margin, view.frame.size.width, height);
    
    [view setFrame:newViewFrame];
}

+ (void)alignParentBottom:(UIView*)view toView:(UIView*)otherView withPadding:(NSInteger)padding {
    
    CGRect newViewFrame = CGRectMake(view.frame.origin.x, view.frame.origin.y, view.frame.size.width, otherView.frame.origin.y + otherView.frame.size.height + padding);
    
    [view setFrame:newViewFrame];
}

@end
