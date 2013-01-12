//
//  TrainCell.m
//  sourcing
//
//  Created by wuzhu on 13-1-12.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "TrainCell.h"
#import "StringUtil.h"
#import "ApplicationSet.h"
#import "UIColor+MGExpanded.h"
#import "TrainVO.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface
@interface TrainCell ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation TrainCell

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitTrainCell
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
        [self commonInitTrainCell];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitTrainCell];
    }
    return self;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods
-(void)setData:(BaseVO *)kdataVO
{
    _dataVO = kdataVO;
    if(_dataVO != nil)
    {
        if([_dataVO isMemberOfClass:[TrainVO class]])
        {
            TrainVO *vo = (TrainVO *)_dataVO;
            [self.laTitle setText:vo.trainname];
            return;
        }
    }
    [self.laTitle setText:@""];
    
}
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - XXXDataSource / XXXDelegate methods


@end
