//
//  NewsHeadlineCell.m
//  sourcing
//
//  Created by wuzhu on 13-1-8.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "NewsHeadlineCell.h"
#import "StringUtil.h"
#import "ApplicationSet.h"
#import "UIImageView+WebCache.h"
#import "UIColor+MGExpanded.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface
@interface NewsHeadlineCell ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties
-(void)setData:(NewsVO *)kdataVO;

@end

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation NewsHeadlineCell

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize
@synthesize dataVO=_dataVO;

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitNewsHeadlineCell
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
        [self commonInitNewsHeadlineCell];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitNewsHeadlineCell];
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
        [self.ivTitlePic setImage:[UIImage imageNamed:@"icon_pic_default"]];
        return;
    }
    [self.laTitle setText:_dataVO.title];
    if (![StringUtil isEmpty:_dataVO.titlepic])
    {
        [self.ivTitlePic setImageWithURL:[NSURL URLWithString:_dataVO.titlepic] placeholderImage:[UIImage imageNamed:@"icon_pic_loadfail"]];
    }
    else if(![StringUtil isEmpty:_dataVO.titlepic_small])
    {
        [self.ivTitlePic setImageWithURL:[NSURL URLWithString:_dataVO.titlepic_small] placeholderImage:[UIImage imageNamed:@"icon_pic_loadfail"]];
     }else
    {
        [self.ivTitlePic setImage:[UIImage imageNamed:@"icon_pic_default"]];
    }
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - XXXDataSource / XXXDelegate methods


@end
