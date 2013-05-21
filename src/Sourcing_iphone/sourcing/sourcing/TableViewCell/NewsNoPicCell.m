//
//  NewsNoPicCell.m
//  sourcing
//
//  Created by wuzhu on 13-1-8.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "NewsNoPicCell.h"
#import "StringUtil.h"
#import "ApplicationSet.h"
#import "UIColor+MGExpanded.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface
@interface NewsNoPicCell ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties
-(void)setData:(NewsVO *)kdataVO;

@end

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation NewsNoPicCell

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize
@synthesize dataVO=_dataVO;

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitNewsNoPicCell
{
    NSMutableArray *colors = [NSMutableArray array];
    [colors addObject:(id)[CCOLOR_TABLEVIEW_SEL CGColor]];
    [colors addObject:(id)[CCOLOR_TABLEVIEW_SEL_2 CGColor]];
    [self setSelectedBackgroundViewGradientColors:colors];
    [self.backgroundView setBackgroundColor:CCOLOR_TABLEVIEW_BG];
    [self setDashWidth:2 dashGap:3 dashStroke:2];
}

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self)
    {
        [self commonInitNewsNoPicCell];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitNewsNoPicCell];
    }
    return self;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods
-(void)setData:(NewsVO *)kdataVO
{
    _dataVO = kdataVO;
    if (_dataVO == nil) {
        [self.laTitle setText:@""];
        [self.laSummary setText:@""];
        return;
    }
    [self.laTitle setText:_dataVO.title];
    if ([StringUtil isEmpty:_dataVO.getNewssummary]) {
        [self.laSummary setText:@""];
    }
    else
    {
        [self.laSummary setText:_dataVO.getNewssummary];
    }
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - XXXDataSource / XXXDelegate methods


@end
