//
//  NotifierCell.m
//  sourcing
//
//  Created by wuzhu on 13-1-12.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "NotifierCell.h"
#import "UIColor+MGExpanded.h"
#import "ApplicationSet.h"
#import "RelativityLaws.h"
#import "OHASBasicHTMLParser.h"
#import "NSAttributedString+Attributes.h"
#import "StringUtil.h"


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface
@interface NotifierCell ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation NotifierCell

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Public *********************************************************************/
@synthesize dataVO = dataVO_;
/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitNotifierCell
{
//    NSMutableArray *colors = [NSMutableArray array];
//    [colors addObject:(id)[CCOLOR_TABLEVIEW_SEL CGColor]];
//    [colors addObject:(id)[CCOLOR_TABLEVIEW_SEL_2 CGColor]];
    //    [self setSelectedBackgroundViewGradientColors:colors];
    [self setSelectionStyle:UITableViewCellSelectionStyleNone];
    [self.backgroundView setBackgroundColor:CCOLOR_TABLEVIEW_BG];
    [self setDashWidth:2 dashGap:3 dashStroke:2];
}

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self)
    {
        [self commonInitNotifierCell];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitNotifierCell];
    }
    return self;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

+(CGFloat)calCellHeight:(NotifierVO *)kvo
{
    if(kvo==nil||[StringUtil isEmpty:kvo.notifiercontent])
        return 60.0f;
    
    OHAttributedLabel *label = [[OHAttributedLabel alloc] initWithFrame:CGRectMake(0,0,312.0f,26.0f)];
    label.numberOfLines = 0;
    label.lineBreakMode = UILineBreakModeCharacterWrap;
    [label setFont:[UIFont boldSystemFontOfSize:16.0f]];
    label.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    label.automaticallyAddLinksForType = NSTextCheckingAllTypes;
    [label setText:kvo.notifiercontent];
    label.attributedText = [OHASBasicHTMLParser attributedStringByProcessingMarkupInAttributedString:label.attributedText];
    [RelativityLaws attributedLabelFitHeight:label];
    CGSize expectedLabelSize = label.frame.size;
    if(expectedLabelSize.height>26.0f)//26.0为默认一行时的高度
        return expectedLabelSize.height+34.0f;//60.0f-26.0f
    else
        return 60.0f;
}

-(void)setData:(NotifierVO *)kvo
{
    dataVO_ = kvo;
    //调整高度
    [self.labelNotifier setText:kvo.notifiercontent];
     self.labelNotifier.attributedText = [OHASBasicHTMLParser attributedStringByProcessingMarkupInAttributedString:self.labelNotifier.attributedText];
    self.labelPublisher.text = self.dataVO.publisher;
    self.labelPublishtime.text = self.dataVO.publishtime;
    [RelativityLaws attributedLabelFitHeight:self.labelNotifier];
    [RelativityLaws alignView:self.labelPublisher below:self.labelNotifier withMargin:4];
    [RelativityLaws alignView:self.labelPublishtime below:self.labelNotifier withMargin:4];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - XXXDataSource / XXXDelegate methods


@end
