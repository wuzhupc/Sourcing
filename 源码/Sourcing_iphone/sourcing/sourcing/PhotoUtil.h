//
//  PhotoUtil.h
//  Fellow
//
//  Created by wuzhu on 12-11-23.
//
//

#import <Foundation/Foundation.h>
#import "MWPhotoBrowser/MWPhotoBrowser.h"

@interface PhotoUtil : NSObject <MWPhotoBrowserDelegate>
{
    NSArray *_photos;
}
@property (nonatomic, retain) NSArray *photos;

- (id)initWithImageUrl:(NSString *)imgurl;
@end
