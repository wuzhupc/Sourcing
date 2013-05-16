//
//  UserInfoCell.m
//  sourcing
//
//  Created by wuzhu on 13-1-14.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "UserInfoCell.h"
#import "UIColor+MGExpanded.h"
#import "ApplicationSet.h"
#import "FavInfoController.h"
#import "UserInfoListViewController.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface
@interface UserInfoCell ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation UserInfoCell

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Public *********************************************************************/
@synthesize userinfotype = userinfotype_;
/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitUserInfoCell
{
//    NSMutableArray *colors = [NSMutableArray array];
//    [colors addObject:(id)[CCOLOR_TABLEVIEW_SEL CGColor]];
//    [colors addObject:(id)[CCOLOR_TABLEVIEW_SEL_2 CGColor]];
//    [self setSelectedBackgroundViewGradientColors:colors];
//    [self.backgroundView setBackgroundColor:CCOLOR_TABLEVIEW_BG];
//    [self setDashWidth:2 dashGap:3 dashStroke:2];
   
}

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self)
    {
        [self commonInitUserInfoCell];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitUserInfoCell];
    }
    return self;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

+(CGFloat)getCellHeight
{
    return 40.0f;
}

-(void)setShowInfo:(NSString *)kmsg userinfotype:(USER_INFO_TYPE)ktype
{
    self.labelUserInfo.text = kmsg;
    self.userinfotype = ktype;
}

-(void)showDetail:(UIViewController *)kvc
{
    if(self.userinfotype == USER_INFO_TYPE_FAV)
    {
        FavInfoController *vc = [[FavInfoController alloc] init];
        [kvc presentModalViewController:vc animated:YES];
        return;
    }else
    {
        UserInfoListViewController *vc = [[UserInfoListViewController alloc] initWithInfoType:self.userinfotype title:[UserVO getUserInfoDesc:self.userinfotype]];
        [kvc presentModalViewController:vc animated:YES];
        return;
    } 
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - XXXDataSource / XXXDelegate methods


@end
