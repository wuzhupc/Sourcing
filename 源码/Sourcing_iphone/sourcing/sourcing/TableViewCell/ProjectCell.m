//
//  ProjectCell.m
//  sourcing
//
//  Created by wuzhu on 13-1-12.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "ProjectCell.h"
#import "StringUtil.h"
#import "ApplicationSet.h"
#import "UIColor+MGExpanded.h"
#import "ResumeVO.h"
#import "JobVO.h"
#import "ProjectVO.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface
@interface ProjectCell ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation ProjectCell

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitProjectCell
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
        [self commonInitProjectCell];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitProjectCell];
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
        if([_dataVO isMemberOfClass:[ResumeVO class]])
        {
            ResumeVO *vo = (ResumeVO *)_dataVO;
            [self.laPublishTime setText:vo.publishtime];
            [self.laSummary setText:vo.expectjob];
            [self.laTitle setText:vo.resumetitle];
        }else if([_dataVO isMemberOfClass:[JobVO class]])
        {
            JobVO *vo = (JobVO *)_dataVO;
            [self.laPublishTime setText:vo.publishtime];
            [self.laSummary setText:vo.company];
            [self.laTitle setText:vo.job];
        }else if([_dataVO isMemberOfClass:[ProjectVO class]])
        {
            ProjectVO *vo = (ProjectVO *)_dataVO;
            [self.laPublishTime setText:vo.publishtime];
            [self.laSummary setText:vo.projectstatus];
            [self.laTitle setText:vo.projectname];
        }
    }else
    {
        [self.laTitle setText:@""];
        [self.laSummary setText:@""];
        [self.laPublishTime setText:@""];
    }
    
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - XXXDataSource / XXXDelegate methods


@end
